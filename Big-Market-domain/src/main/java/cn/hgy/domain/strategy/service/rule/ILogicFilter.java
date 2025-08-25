package cn.hgy.domain.strategy.service.rule;

import cn.hgy.domain.strategy.model.entity.RuleActionEntity;
import cn.hgy.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-25
 * @Description: 抽奖规则过滤接口
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
