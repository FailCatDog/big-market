package cn.hgy.test.infrastructure;

import cn.hgy.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.hgy.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.hgy.domain.rebate.model.entity.TaskEntity;
import cn.hgy.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hgy.domain.rebate.model.valobj.DailyBehaviorRebateVO;
import cn.hgy.domain.rebate.model.valobj.TaskStateVO;
import cn.hgy.domain.rebate.repository.IBehaviorRebateRepository;
import cn.hgy.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为返利仓储测试 - 验证ShardingSphere分库分表
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BehaviorRebateRepositoryTest {

    @Autowired
    private IBehaviorRebateRepository behaviorRebateRepository;

    @Test
    public void test_queryDailyBehaviorRebateConfig() {
        List<DailyBehaviorRebateVO> dailyBehaviorRebateVOS = behaviorRebateRepository.queryDailyBehaviorRebateConfig(BehaviorTypeVO.SIGN);
        log.info("测试结果：{}", dailyBehaviorRebateVOS);
    }

    @Test
    public void test_saveUserRebateRecord() {
        // 构建聚合对象
        BehaviorRebateOrderEntity behaviorRebateOrderEntity = BehaviorRebateOrderEntity.builder()
                .userId("xiaofuge")
                .orderId("20241026001")
                .behaviorType(BehaviorTypeVO.SIGN.getCode())
                .rebateDesc("签到返利")
                .rebateType("sku")
                .rebateConfig("9011")
                .bizId("xiaofuge_sign_20241026")
                .build();

        TaskEntity taskEntity = TaskEntity.builder()
                .userId("xiaofuge")
                .topic("send_rebate")
                .messageId("20241026001")
                .message(new BaseEvent.EventMessage<>())
                .state(TaskStateVO.create)
                .build();

        BehaviorRebateAggregate behaviorRebateAggregate = BehaviorRebateAggregate.builder()
                .userId("xiaofuge")
                .behaviorRebateOrderEntity(behaviorRebateOrderEntity)
                .taskEntity(taskEntity)
                .build();

        List<BehaviorRebateAggregate> behaviorRebateAggregates = new ArrayList<>();
        behaviorRebateAggregates.add(behaviorRebateAggregate);

        try {
            behaviorRebateRepository.saveUserRebateRecord("xiaofuge", behaviorRebateAggregates);
            log.info("测试完成 - 用户行为返利记录保存成功，ShardingSphere自动分库分表");
        } catch (Exception e) {
            log.error("测试失败", e);
        }
    }

    @Test
    public void test_saveUserRebateRecord_MultipleUsers() {
        // 测试多个用户的分库分表
        String[] userIds = {"user001", "user002", "user003", "user004"};
        
        for (String userId : userIds) {
            BehaviorRebateOrderEntity behaviorRebateOrderEntity = BehaviorRebateOrderEntity.builder()
                    .userId(userId)
                    .orderId("order_" + userId + "_001")
                    .behaviorType(BehaviorTypeVO.SIGN.getCode())
                    .rebateDesc("签到返利")
                    .rebateType("sku")
                    .rebateConfig("9011")
                    .bizId(userId + "_sign_20241026")
                    .build();

            TaskEntity taskEntity = TaskEntity.builder()
                    .userId(userId)
                    .topic("send_rebate")
                    .messageId("msg_" + userId + "_001")
                    .message(new BaseEvent.EventMessage<>())
                    .state(TaskStateVO.create)
                    .build();

            BehaviorRebateAggregate behaviorRebateAggregate = BehaviorRebateAggregate.builder()
                    .userId(userId)
                    .behaviorRebateOrderEntity(behaviorRebateOrderEntity)
                    .taskEntity(taskEntity)
                    .build();

            List<BehaviorRebateAggregate> behaviorRebateAggregates = new ArrayList<>();
            behaviorRebateAggregates.add(behaviorRebateAggregate);

            try {
                behaviorRebateRepository.saveUserRebateRecord(userId, behaviorRebateAggregates);
                log.info("用户 {} 的返利记录保存成功，ShardingSphere根据userId自动路由", userId);
            } catch (Exception e) {
                log.error("用户 {} 的返利记录保存失败", userId, e);
            }
        }
    }
}
