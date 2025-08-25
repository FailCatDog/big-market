package cn.hgy.domain.strategy.service.armory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-23
 * @Description: 策略抽奖调度接口
 */
public interface IStrategyDispatch {

    /**
     * 获取抽奖策略装配的随机结果
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
