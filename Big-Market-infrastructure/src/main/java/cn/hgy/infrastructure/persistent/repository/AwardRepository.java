package cn.hgy.infrastructure.persistent.repository;

import cn.hgy.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.hgy.domain.award.model.entity.TaskEntity;
import cn.hgy.domain.award.model.entity.UserAwardRecordEntity;
import cn.hgy.domain.award.repository.IAwardRepository;
import cn.hgy.infrastructure.event.EventPublisher;
import cn.hgy.infrastructure.persistent.dao.ITaskDao;
import cn.hgy.infrastructure.persistent.dao.IUserAwardRecordDao;
import cn.hgy.infrastructure.persistent.po.Task;
import cn.hgy.infrastructure.persistent.po.UserAwardRecord;
import cn.hgy.types.enums.ResponseCode;
import cn.hgy.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 用户抽奖记录仓储实现
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AwardRepository implements IAwardRepository {

    private final IUserAwardRecordDao userAwardRecordDao;
    private final ITaskDao taskDao;
    private final TransactionTemplate transactionTemplate;
    private EventPublisher eventPublisher;

    @Override
    public void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate) {

        UserAwardRecordEntity userAwardRecordEntity = userAwardRecordAggregate.getUserAwardRecordEntity();
        TaskEntity taskEntity = userAwardRecordAggregate.getTaskEntity();
        String userId = userAwardRecordEntity.getUserId();
        Long activityId = userAwardRecordEntity.getActivityId();
        Integer awardId = userAwardRecordEntity.getAwardId();

        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setUserId(userAwardRecordEntity.getUserId());
        userAwardRecord.setActivityId(userAwardRecordEntity.getActivityId());
        userAwardRecord.setStrategyId(userAwardRecordEntity.getStrategyId());
        userAwardRecord.setOrderId(userAwardRecordEntity.getOrderId());
        userAwardRecord.setAwardId(userAwardRecordEntity.getAwardId());
        userAwardRecord.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        userAwardRecord.setAwardTime(userAwardRecordEntity.getAwardTime());
        userAwardRecord.setAwardState(userAwardRecordEntity.getAwardState().getCode());

        Task task = new Task();
        task.setUserId(taskEntity.getUserId());
        task.setTopic(taskEntity.getTopic());
        task.setMessageId(taskEntity.getMessageId());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setState(taskEntity.getState().getCode());

        // ShardingSphere会根据userId自动进行分库分表路由
        transactionTemplate.execute(status -> {
            try {
                // 写入记录
                userAwardRecordDao.insert(userAwardRecord);
                // 写入任务
                taskDao.insert(task);
                return 1;
            } catch (DuplicateKeyException e) {
                status.setRollbackOnly();
                log.error("写入中奖记录，唯一索引冲突 userId: {} activityId: {} awardId: {}", userId, activityId, awardId, e);
                throw new AppException(ResponseCode.INDEX_DUP.getCode(), e);
            }
        });

        try {
            // 发送消息【在事务外执行，如果失败还有任务补偿】
            eventPublisher.publish(task.getTopic(), task.getMessage());
            // 更新数据库记录，task 任务表
            taskDao.updateTaskSendMessageCompleted(task);
        } catch (Exception e) {
            log.error("写入中奖记录，发送MQ消息失败 userId: {} topic: {}", userId, task.getTopic());
            taskDao.updateTaskSendMessageFail(task);
        }

    }
}
