package cn.hgy.domain.strategy.service.armory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略装配库（兵工厂），负责初始化策略计算
 */
public interface IStrategyArmory {

    /**
     * 装配抽奖策略配置 TODO
     * @param strategyId 策略ID
     */
    boolean assembleLotteryStrategy(Long strategyId);


}
