package cn.hgy.domain.activity.service;

import cn.hgy.domain.activity.model.entity.ActivityCountEntity;
import cn.hgy.domain.activity.model.entity.ActivityEntity;
import cn.hgy.domain.activity.model.entity.ActivitySkuEntity;
import cn.hgy.domain.activity.repository.IActivityRepository;
import cn.hgy.domain.activity.service.rule.factory.DefaultActivityChainFactory;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 抽奖活动支撑类
 */
public class RaffleActivitySupport {
    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }

    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }
}
