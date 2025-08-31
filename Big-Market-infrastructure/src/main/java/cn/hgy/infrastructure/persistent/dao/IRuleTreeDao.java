package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-29
 * @Description: 规则树表DAO
 */
@Mapper
public interface IRuleTreeDao {
    RuleTree queryRuleTreeByTreeId(String treeId);
}
