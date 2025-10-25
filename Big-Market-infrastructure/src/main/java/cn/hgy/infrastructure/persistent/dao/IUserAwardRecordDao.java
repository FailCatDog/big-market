package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.UserAwardRecord;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-30
 * @Description: 用户中奖记录表
 */
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);
}
