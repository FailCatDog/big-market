package cn.hgy.domain.strategy.service.rule.chain;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-27
 * @Description: 装配接口
 */
public interface ILogicChainArmory {

    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
