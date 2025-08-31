package cn.hgy.domain.strategy.service.tree;

import cn.hgy.domain.strategy.service.tree.factory.DefaultTreeFactory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 规则树接口
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);
}
