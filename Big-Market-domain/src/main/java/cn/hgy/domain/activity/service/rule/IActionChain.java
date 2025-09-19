package cn.hgy.domain.activity.service.rule;

import cn.hgy.domain.activity.model.entity.ActivityCountEntity;
import cn.hgy.domain.activity.model.entity.ActivityEntity;
import cn.hgy.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 下单规则过滤接口
 */
public interface IActionChain extends IActionChainArmory {

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
