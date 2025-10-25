package cn.hgy.domain.strategy.service.tree.impl;

import cn.hgy.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hgy.domain.strategy.repository.IStrategyRepository;
import cn.hgy.domain.strategy.service.tree.ILogicTreeNode;
import cn.hgy.domain.strategy.service.tree.factory.DefaultTreeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-28
 * @Description: 次数锁节点
 */
@Slf4j
@Component("rule_lock")
@RequiredArgsConstructor
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    private final IStrategyRepository repository;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);

        long raffleCount = 0L;
        try {
            raffleCount = Long.parseLong(ruleValue);
        } catch (Exception e) {
            throw new RuntimeException("规则过滤-次数锁异常 ruleValue: " + ruleValue + " 配置不正确");
        }

        // 查询用户抽奖次数 - 当天的； 策略ID：活动ID 1:1 的配置，可以直接用strategyId查询
        Integer userRaffleCount = repository.queryTodayUserRaffleCount(userId, strategyId);

        // 用户抽奖次数大于规则限定值，规则放行
        if (userRaffleCount >= raffleCount) {
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }

        // 用户抽奖次数小于规则限定值，规则拦截
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
