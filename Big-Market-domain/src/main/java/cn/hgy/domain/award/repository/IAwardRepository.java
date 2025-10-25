package cn.hgy.domain.award.repository;

import cn.hgy.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 用户中奖仓储接口
 */
public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
