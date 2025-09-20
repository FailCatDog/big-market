package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.RaffleActivityAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-17
 * @Description: 抽奖活动账户表
 */
@Mapper
public interface IRaffleActivityAccountDao {
    int updateAccountQuota(RaffleActivityAccount raffleActivityAccount);

    void insert(RaffleActivityAccount raffleActivityAccount);
}
