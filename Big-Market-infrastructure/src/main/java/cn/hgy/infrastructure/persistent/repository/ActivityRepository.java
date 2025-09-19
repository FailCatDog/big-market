package cn.hgy.infrastructure.persistent.repository;

import cn.hgy.domain.activity.model.entity.ActivityCountEntity;
import cn.hgy.domain.activity.model.entity.ActivityEntity;
import cn.hgy.domain.activity.model.entity.ActivitySkuEntity;
import cn.hgy.domain.activity.model.valobj.ActivityStateVO;
import cn.hgy.domain.activity.repository.IActivityRepository;
import cn.hgy.infrastructure.persistent.dao.IRaffleActivityCountDao;
import cn.hgy.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.hgy.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import cn.hgy.infrastructure.persistent.po.RaffleActivity;
import cn.hgy.infrastructure.persistent.po.RaffleActivityCount;
import cn.hgy.infrastructure.persistent.po.RaffleActivitySku;
import cn.hgy.infrastructure.persistent.redis.IRedisService;
import cn.hgy.types.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 活动仓储服务
 */
@Repository
@RequiredArgsConstructor
public class ActivityRepository implements IActivityRepository {

    private final IRedisService redisService;
    private final IRaffleActivityDao raffleActivityDao;
    private final IRaffleActivitySkuDao raffleActivitySkuDao;
    private final IRaffleActivityCountDao raffleActivityCountDao;

    @Override
    public ActivitySkuEntity queryActivitySku(Long sku) {
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        return ActivitySkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
    }

    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if (null != activityEntity) return activityEntity;
        // 从库中获取数据
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .build();
        redisService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if (null != activityCountEntity) return activityCountEntity;
        // 从库中获取数据
        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        redisService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }

}

