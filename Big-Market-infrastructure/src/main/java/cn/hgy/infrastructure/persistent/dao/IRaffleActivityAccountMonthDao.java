package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.RaffleActivityAccountMonth;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-30
 * @Description: 抽奖活动账户表-月次数
 */
public interface IRaffleActivityAccountMonthDao {
    int updateActivityAccountMonthSubtractionQuota(RaffleActivityAccountMonth build);

    void insertActivityAccountMonth(RaffleActivityAccountMonth build);

    RaffleActivityAccountMonth queryActivityAccountMonthByUserId(RaffleActivityAccountMonth raffleActivityAccountMonthReq);
}
