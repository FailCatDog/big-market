package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.UserRaffleOrder;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-30
 * @Description: 用户抽奖订单表
 */
public interface IUserRaffleOrderDao {
    void insert(UserRaffleOrder build);

    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrderReq);
}
