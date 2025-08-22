package cn.hgy.domain.strategy.service.armory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略条件实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyConditionEntity {

    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Integer strategyId;
}
