package cn.hgy.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 活动状态值对象
 */
@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    creat("create", "创建"),
    open("open", "开启"),
    close("close", "关闭")
    ;

    private final String code;
    private final String desc;
}
