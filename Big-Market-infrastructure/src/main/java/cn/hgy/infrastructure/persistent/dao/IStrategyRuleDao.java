package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.StrategyAward;
import cn.hgy.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-21
 * @Description: 策略规则 DAO
 */
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRule> queryStrategyRuleList();

}
