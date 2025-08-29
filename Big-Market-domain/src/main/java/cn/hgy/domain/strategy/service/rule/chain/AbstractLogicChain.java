package cn.hgy.domain.strategy.service.rule.chain;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-27
 * @Description:
 */
public abstract class AbstractLogicChain implements ILogicChain{

    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    protected abstract String ruleModel();
}
