package cn.hgy.domain.activity.service;

import cn.hgy.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.hgy.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 抽奖活动参与服务借口
 */
public interface IRaffleActivityPartakeService {

    UserRaffleOrderEntity createOrder(String userId, Long activityId);

    /**
     * 创建抽奖单：用户参与抽奖活动，扣减活动账户库存，产生抽奖单。如存在未被使用的抽奖单则直接返回已存在的抽奖单。
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);
}
