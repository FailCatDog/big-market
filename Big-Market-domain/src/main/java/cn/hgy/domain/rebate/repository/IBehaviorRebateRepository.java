package cn.hgy.domain.rebate.repository;

import cn.hgy.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.hgy.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hgy.domain.rebate.model.valobj.DailyBehaviorRebateVO;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为返利服务仓储接口
 */
public interface IBehaviorRebateRepository {
    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);

    void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates);
}
