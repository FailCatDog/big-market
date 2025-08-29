package cn.hgy.domain.strategy.service.rule.filter.impl;

import cn.hgy.domain.strategy.annotation.LogicStrategy;
import cn.hgy.domain.strategy.model.entity.RuleActionEntity;
import cn.hgy.domain.strategy.model.entity.RuleMatterEntity;
import cn.hgy.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hgy.domain.strategy.repository.IStrategyRepository;
import cn.hgy.domain.strategy.service.rule.ILogicFilter;
import cn.hgy.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-25
 * @Description: 用户抽奖N次后，对应奖品可解锁抽奖
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_LOCK)
@RequiredArgsConstructor
public class RuleLockLogicFilter implements ILogicFilter<RuleActionEntity.RaffleCenterEntity> {

    private final IStrategyRepository repository;

    // 用户抽奖次数，后续完成这部分流程开发的时候，从数据库/Redis中读取
    private Long userRaffleCount = 0L;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleCenterEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        // 查询规则值配置；当前奖品ID，抽奖中规则对应的校验值。如；1、2、6
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        long raffleCount = Long.parseLong(ruleValue);

        // 用户抽奖次数大于规则限定值，规则放行
        if (userRaffleCount>= raffleCount) {
            return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 用户抽奖次数小于规则限定值，规则拦截
        return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();
    }
}
