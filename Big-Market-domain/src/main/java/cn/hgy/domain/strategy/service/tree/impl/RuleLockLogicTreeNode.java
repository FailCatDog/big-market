package cn.hgy.domain.strategy.service.tree.impl;

import cn.hgy.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hgy.domain.strategy.service.tree.ILogicTreeNode;
import cn.hgy.domain.strategy.service.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 次数锁节点
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
