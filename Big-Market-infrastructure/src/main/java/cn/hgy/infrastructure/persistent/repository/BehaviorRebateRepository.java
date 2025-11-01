package cn.hgy.infrastructure.persistent.repository;

import cn.hgy.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.hgy.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.hgy.domain.rebate.model.entity.TaskEntity;
import cn.hgy.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hgy.domain.rebate.model.valobj.DailyBehaviorRebateVO;
import cn.hgy.domain.rebate.repository.IBehaviorRebateRepository;
import cn.hgy.infrastructure.event.EventPublisher;
import cn.hgy.infrastructure.persistent.dao.IDailyBehaviorRebateDao;
import cn.hgy.infrastructure.persistent.dao.ITaskDao;
import cn.hgy.infrastructure.persistent.dao.IUserBehaviorRebateOrderDao;
import cn.hgy.infrastructure.persistent.po.DailyBehaviorRebate;
import cn.hgy.infrastructure.persistent.po.Task;
import cn.hgy.infrastructure.persistent.po.UserBehaviorRebateOrder;
import cn.hgy.types.enums.ResponseCode;
import cn.hgy.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为返利服务仓储实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BehaviorRebateRepository implements IBehaviorRebateRepository {

    private final IDailyBehaviorRebateDao dailyBehaviorRebateDao;
    private final IUserBehaviorRebateOrderDao userBehaviorRebateOrderDao;
    private final ITaskDao taskDao;
    private final TransactionTemplate transactionTemplate;
    private final EventPublisher eventPublisher;

    @Override
    public List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO) {
        List<DailyBehaviorRebate> dailyBehaviorRebates = dailyBehaviorRebateDao.queryDailyBehaviorRebateByBehaviorType(behaviorTypeVO.getCode());
        List<DailyBehaviorRebateVO> dailyBehaviorRebateVOS = new ArrayList<>(dailyBehaviorRebates.size());
        for (DailyBehaviorRebate dailyBehaviorRebate : dailyBehaviorRebates) {
            dailyBehaviorRebateVOS.add(DailyBehaviorRebateVO.builder()
                    .behaviorType(dailyBehaviorRebate.getBehaviorType())
                    .rebateDesc(dailyBehaviorRebate.getRebateDesc())
                    .rebateType(dailyBehaviorRebate.getRebateType())
                    .rebateConfig(dailyBehaviorRebate.getRebateConfig())
                    .build());
        }
        return dailyBehaviorRebateVOS;
    }

    @Override
    public void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates) {
        // ShardingSphere会根据userId自动进行分库分表路由
        transactionTemplate.execute(status -> {
            try {
                for (BehaviorRebateAggregate behaviorRebateAggregate : behaviorRebateAggregates) {
                    BehaviorRebateOrderEntity behaviorRebateOrderEntity = behaviorRebateAggregate.getBehaviorRebateOrderEntity();
                    // 用户行为返利订单对象
                    UserBehaviorRebateOrder userBehaviorRebateOrder = new UserBehaviorRebateOrder();
                    userBehaviorRebateOrder.setUserId(behaviorRebateOrderEntity.getUserId());
                    userBehaviorRebateOrder.setOrderId(behaviorRebateOrderEntity.getOrderId());
                    userBehaviorRebateOrder.setBehaviorType(behaviorRebateOrderEntity.getBehaviorType());
                    userBehaviorRebateOrder.setRebateDesc(behaviorRebateOrderEntity.getRebateDesc());
                    userBehaviorRebateOrder.setRebateType(behaviorRebateOrderEntity.getRebateType());
                    userBehaviorRebateOrder.setRebateConfig(behaviorRebateOrderEntity.getRebateConfig());
                    userBehaviorRebateOrder.setBizId(behaviorRebateOrderEntity.getBizId());
                    userBehaviorRebateOrderDao.insert(userBehaviorRebateOrder);

                    // 任务对象
                    TaskEntity taskEntity = behaviorRebateAggregate.getTaskEntity();
                    Task task = new Task();
                    task.setUserId(taskEntity.getUserId());
                    task.setTopic(taskEntity.getTopic());
                    task.setMessageId(taskEntity.getMessageId());
                    task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
                    task.setState(taskEntity.getState().getCode());
                    taskDao.insert(task);
                }
                return 1;
            } catch (DuplicateKeyException e) {
                status.setRollbackOnly();
                log.error("写入返利记录，唯一索引冲突 userId: {}", userId, e);
                throw new AppException(ResponseCode.INDEX_DUP.getCode(), e);
            }
        });

        // 同步发送MQ消息
        for (BehaviorRebateAggregate behaviorRebateAggregate : behaviorRebateAggregates) {
            TaskEntity taskEntity = behaviorRebateAggregate.getTaskEntity();
            Task task = new Task();
            task.setUserId(taskEntity.getUserId());
            task.setMessageId(taskEntity.getMessageId());
            try {
                // 发送消息【在事务外执行，如果失败还有任务补偿】
                eventPublisher.publish(taskEntity.getTopic(), taskEntity.getMessage());
                // 更新数据库记录，task 任务表
                taskDao.updateTaskSendMessageCompleted(task);
            } catch (Exception e) {
                log.error("写入返利记录，发送MQ消息失败 userId: {} topic: {}", userId, task.getTopic());
                taskDao.updateTaskSendMessageFail(task);
            }
        }

    }

}