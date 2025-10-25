package cn.hgy.trigger.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-25
 * @Description:
 */
@Data
@Builder
public class ActivityDrawResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 排序编号【策略奖品配置的奖品顺序编号】
    private Integer awardIndex;
}
