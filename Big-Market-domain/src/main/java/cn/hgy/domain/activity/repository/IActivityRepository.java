package cn.hgy.domain.activity.repository;

import cn.hgy.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.hgy.domain.activity.model.entity.ActivityCountEntity;
import cn.hgy.domain.activity.model.entity.ActivityEntity;
import cn.hgy.domain.activity.model.entity.ActivitySkuEntity;
import cn.hgy.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-19
 * @Description: 活动仓储接口
 */
public interface IActivityRepository {
    ActivitySkuEntity queryActivitySku(Long sku);

    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);

    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO build);

    ActivitySkuStockKeyVO takeQueueValue();

    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);
}
