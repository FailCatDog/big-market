package cn.hgy.domain.strategy.service.rule.chain;

import cn.hgy.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-27
 * @Description: 责任链接口
 */
public interface ILogicChain extends ILogicChainArmory{

    /**
     * 责任链接口
     *
     * @param userId     用户Id
     * @param strategyId 策略Id
     * @return 奖品Id
     */
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);
}
