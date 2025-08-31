package cn.hgy.domain.strategy.service.tree.factory.engine;

import cn.hgy.domain.strategy.service.tree.factory.DefaultTreeFactory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 规则树组合接口
 */
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId);
}
