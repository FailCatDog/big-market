package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-29
 * @Description:
 */
@Mapper
public interface IRuleTreeNodeLineDao {
    List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);
}
