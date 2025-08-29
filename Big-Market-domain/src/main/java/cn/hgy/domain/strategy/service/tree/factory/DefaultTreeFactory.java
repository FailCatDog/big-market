package cn.hgy.domain.strategy.service.tree.factory;

import cn.hgy.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hgy.domain.strategy.model.valobj.RuleTreeVO;
import cn.hgy.domain.strategy.service.tree.ILogicTreeNode;
import cn.hgy.domain.strategy.service.tree.factory.engine.IDecisionTreeEngine;
import cn.hgy.domain.strategy.service.tree.factory.engine.impl.DecisionTreeEngine;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 规则树工厂
 */
@Service
@RequiredArgsConstructor
public class DefaultTreeFactory {
    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO) {
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }

    /**
     * 决策树个动作实习
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardData strategyAwardData;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardData {
        /** 抽奖奖品ID - 内部流转使用 */
        private Integer awardId;
        /** 抽奖奖品规则 */
        private String awardRuleValue;
    }
}
