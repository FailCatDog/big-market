package cn.hgy.trigger.api.dto;

import lombok.Data;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-02
 * @Description: 抽奖奖品列表 请求对象
 */
@Data
public class RaffleAwardListRequestDTO {
    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;
}
