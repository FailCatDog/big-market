package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.RaffleActivityAccountDay;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-30
 * @Description: 抽奖活动账户表-日次数
 */
public interface IRaffleActivityAccountDayDao {
    int updateActivityAccountDaySubtractionQuota(RaffleActivityAccountDay build);

    void insertActivityAccountDay(RaffleActivityAccountDay build);

    RaffleActivityAccountDay queryActivityAccountDayByUserId(RaffleActivityAccountDay raffleActivityAccountDayReq);
}
