package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.DailyBehaviorRebate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 日常行为返利活动配置
 */
@Mapper
public interface IDailyBehaviorRebateDao {
    List<DailyBehaviorRebate> queryDailyBehaviorRebateByBehaviorType(String behaviorType);
}
