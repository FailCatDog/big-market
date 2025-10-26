package cn.hgy.domain.activity.model.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 参与抽奖活动实体对象
 */
@Data
@Builder
public class PartakeRaffleActivityEntity {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

}
