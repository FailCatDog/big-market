package cn.hgy.domain.strategy.service.tree.impl;

import cn.hgy.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hgy.domain.strategy.service.tree.ILogicTreeNode;
import cn.hgy.domain.strategy.service.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 兜底奖励节点
 */
@Component("rule_lock_award")
@Slf4j
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardData(DefaultTreeFactory.StrategyAwardData.builder()
                        .awardId(101)
                        .awardRuleValue("1,100")
                        .build())
                .build();
    }
}
