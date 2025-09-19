package cn.hgy.domain.activity.service;

import cn.hgy.domain.activity.model.entity.ActivityOrderEntity;
import cn.hgy.domain.activity.model.entity.ActivityShopCartEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 抽奖活动订单接口
 */
public interface IRaffleOrder {

    /**
     * 以 sku 创建抽奖活动订单，获得参与抽奖资格（可消耗的次数）
     * @param activityShopCartEntity 活动sku实体，通过sku领取活动
     * @return 活动参与记录实体
     */
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);
}
