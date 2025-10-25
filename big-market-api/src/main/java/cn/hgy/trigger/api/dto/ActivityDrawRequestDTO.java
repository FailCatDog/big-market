package cn.hgy.trigger.api.dto;

import lombok.Data;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-25
 * @Description: 活动抽奖请求对象
 */
@Data
public class ActivityDrawRequestDTO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;
}
