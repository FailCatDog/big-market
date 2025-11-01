package cn.hgy.domain.rebate.model.entity;

import cn.hgy.domain.rebate.model.valobj.BehaviorTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 行为类型；sign 签到、openai_pay 支付
     */
    private BehaviorTypeVO behaviorTypeVO;
    /**
     * 业务ID；签到则是日期字符串，支付则是外部的业务ID
     */
    private String outBusinessNo;
}
