package cn.hgy.domain.strategy.service.rule.chain.impl;

import cn.hgy.domain.strategy.service.armory.IStrategyDispatch;
import cn.hgy.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-27
 * @Description: 兜底责任链节点
 */
@Slf4j
@Component("default")
@RequiredArgsConstructor
public class DefaultLogicChain extends AbstractLogicChain {

    protected final IStrategyDispatch strategyDispatch;

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return awardId;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }
}
