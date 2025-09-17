package cn.hgy.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-02
 * @Description: 抽奖应答对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 排序编号
    private Integer awardIndex;
}
