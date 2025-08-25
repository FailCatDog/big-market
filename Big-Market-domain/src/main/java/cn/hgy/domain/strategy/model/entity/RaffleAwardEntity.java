package cn.hgy.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-25
 * @Description: 奖品实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    private Long strategyId;
    private Integer awardId;
    /** 奖品对接标识 */
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
}
