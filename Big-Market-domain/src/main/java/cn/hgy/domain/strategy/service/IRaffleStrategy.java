package cn.hgy.domain.strategy.service;

import cn.hgy.domain.strategy.model.entity.RaffleAwardEntity;
import cn.hgy.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-25
 * @Description: 抽奖策略接口
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
