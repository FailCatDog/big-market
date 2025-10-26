package cn.hgy.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-25
 * @Description: 抽奖因子
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {

    private String userId;
    private Long strategyId;
    private Integer awardId;
    private Date endDateTime;

}
