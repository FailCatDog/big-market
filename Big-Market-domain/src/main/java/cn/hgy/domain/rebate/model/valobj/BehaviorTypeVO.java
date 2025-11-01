package cn.hgy.domain.rebate.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为类型枚举值对象
 */
@Getter
@AllArgsConstructor
public enum BehaviorTypeVO {

    SIGN("sign", "签到（日历）"),
    OPENAI_PAY("openai_pay", "openai 外部支付完成"),
    ;

    private final String code;
    private final String info;

}
