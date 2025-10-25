package cn.hgy.domain.strategy.service.armory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略装配库（兵工厂），负责初始化策略计算
 */
public interface IStrategyArmory {

    /**
     * 装配抽奖策略配置【触发的时机可以为活动审核通过后进行调用】
     * @param activityId
     * @return
     */
    boolean assembleLotteryStrategyByActivityId(Long activityId);

    /**
     * 装配抽奖策略配置
     * @param strategyId 策略ID
     */
    boolean assembleLotteryStrategy(Long strategyId);


}
