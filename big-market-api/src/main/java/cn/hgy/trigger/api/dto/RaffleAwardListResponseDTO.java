package cn.hgy.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-02
 * @Description: 抽奖奖品列表 应答对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListResponseDTO {
    // 奖品Id
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 奖品副标题（抽奖一次后解锁）
    private String awardSubTitle;
    // 排序编号
    private Integer sort;
}
