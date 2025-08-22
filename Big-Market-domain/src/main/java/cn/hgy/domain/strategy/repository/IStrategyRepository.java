package cn.hgy.domain.strategy.repository;

import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略仓储接口
 */
@Service
public interface IStrategyRepository {


    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);
}
