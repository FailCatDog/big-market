package cn.hgy.domain.strategy;

import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-02
 * @Description: 策略奖品接口
 */
public interface IRaffleAward {

    /**
     * 根据策略ID查询抽奖奖品列表配置
     * @param strategyId 策略ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);

    /**
     * 根据策略ID查询抽奖奖品列表配置
     * @param activityId 活动ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);
}
