package cn.hgy.domain.award.service;

import cn.hgy.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 奖品服务接口
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
}
