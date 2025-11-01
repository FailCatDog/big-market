package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.UserBehaviorRebateOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 用户行为返利流水订单表
 */
@Mapper
public interface IUserBehaviorRebateOrderDao {
    void insert(UserBehaviorRebateOrder userBehaviorRebateOrder);
}
