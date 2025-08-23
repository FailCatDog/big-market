package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.Strategy;
import cn.hgy.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-21
 * @Description: 抽奖策略 DAO
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();
}
