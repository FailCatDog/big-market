package cn.hgy.infrastructure.persistent.repository;

import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hgy.domain.strategy.repository.IStrategyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略仓储实现类
 */
@RequiredArgsConstructor
public class StrategyRepository implements IStrategyRepository {
//    private IStrategyA
    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        return List.of();
    }
}
