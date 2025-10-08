package cn.hgy.domain.activity.service.quota.rule;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 抽奖动作责任链装配
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);
}
