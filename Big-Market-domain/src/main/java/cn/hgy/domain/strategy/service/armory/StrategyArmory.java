package cn.hgy.domain.strategy.service.armory;

import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hgy.domain.strategy.repository.IStrategyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略装配库（兵工厂），负责初始化策略计算
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StrategyArmory implements IStrategyArmory{

    private final IStrategyRepository repository;

    @Override
    public void assembleLotteryStrategy(Long strategyId) {

        // 1. 查询策略配置
       List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);
    }
}
