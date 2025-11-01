package cn.hgy.test.infrastructure;

import cn.hgy.domain.activity.model.entity.ActivityEntity;
import cn.hgy.domain.activity.model.entity.ActivityCountEntity;
import cn.hgy.domain.activity.repository.IActivityRepository;
import cn.hgy.domain.strategy.model.entity.StrategyEntity;
import cn.hgy.domain.strategy.repository.IStrategyRepository;
import cn.hgy.infrastructure.persistent.redis.IRedisService;
import cn.hgy.types.common.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-25
 * @Description: 缓存优化测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheOptimizationTest {

    @Resource
    private IActivityRepository activityRepository;
    
    @Resource
    private IStrategyRepository strategyRepository;
    
    @Resource
    private IRedisService redisService;

    /**
     * 测试活动缓存过期时间设置
     */
    @Test
    public void test_activity_cache_expiration() {
        Long activityId = 100301L;
        
        // 清除缓存
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        redisService.remove(cacheKey);
        
        log.info("开始测试活动缓存过期时间设置");
        
        // 第一次查询，会从数据库加载并设置缓存
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        log.info("活动信息: {}", activityEntity);
        
        // 检查缓存是否存在
        boolean exists = redisService.isExists(cacheKey);
        log.info("缓存是否存在: {}", exists);
        
        // 第二次查询，应该从缓存获取
        ActivityEntity cachedActivity = activityRepository.queryRaffleActivityByActivityId(activityId);
        log.info("从缓存获取的活动信息: {}", cachedActivity);
    }

    /**
     * 测试活动次数配置缓存过期时间设置
     */
    @Test
    public void test_activity_count_cache_expiration() {
        Long activityCountId = 11101L;
        
        // 清除缓存
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        redisService.remove(cacheKey);
        
        log.info("开始测试活动次数配置缓存过期时间设置");
        
        // 第一次查询，会从数据库加载并设置缓存
        ActivityCountEntity activityCountEntity = activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
        log.info("活动次数配置信息: {}", activityCountEntity);
        
        // 检查缓存是否存在
        boolean exists = redisService.isExists(cacheKey);
        log.info("缓存是否存在: {}", exists);
        
        // 第二次查询，应该从缓存获取
        ActivityCountEntity cachedActivityCount = activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
        log.info("从缓存获取的活动次数配置信息: {}", cachedActivityCount);
    }

    /**
     * 测试策略缓存过期时间设置
     */
    @Test
    public void test_strategy_cache_expiration() {
        Long strategyId = 100006L;
        
        // 清除缓存
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        redisService.remove(cacheKey);
        
        log.info("开始测试策略缓存过期时间设置");
        
        // 第一次查询，会从数据库加载并设置缓存
        StrategyEntity strategyEntity = strategyRepository.queryStrategyEntityByStrategyId(strategyId);
        log.info("策略信息: {}", strategyEntity);
        
        // 检查缓存是否存在
        boolean exists = redisService.isExists(cacheKey);
        log.info("缓存是否存在: {}", exists);
        
        // 第二次查询，应该从缓存获取
        StrategyEntity cachedStrategy = strategyRepository.queryStrategyEntityByStrategyId(strategyId);
        log.info("从缓存获取的策略信息: {}", cachedStrategy);
    }

    /**
     * 测试缓存过期时间计算
     */
    @Test
    public void test_cache_expiration_calculation() {
        log.info("测试缓存过期时间计算");
        
        // 测试不同的时间场景
        long currentTime = System.currentTimeMillis();
        
        // 场景1：活动还有1天结束
        long endTime1 = currentTime + TimeUnit.DAYS.toMillis(1);
        long expireTime1 = endTime1 - currentTime + TimeUnit.DAYS.toMillis(1);
        log.info("活动还有1天结束，缓存过期时间: {} 小时", TimeUnit.MILLISECONDS.toHours(expireTime1));
        
        // 场景2：活动还有7天结束
        long endTime2 = currentTime + TimeUnit.DAYS.toMillis(7);
        long expireTime2 = endTime2 - currentTime + TimeUnit.DAYS.toMillis(1);
        log.info("活动还有7天结束，缓存过期时间: {} 天", TimeUnit.MILLISECONDS.toDays(expireTime2));
        
        // 场景3：活动已结束
        long endTime3 = currentTime - TimeUnit.DAYS.toMillis(1);
        if (endTime3 <= currentTime) {
            log.info("活动已结束，使用短期缓存: 1小时");
        }
    }

    /**
     * 测试库存更新优化
     */
    @Test
    public void test_stock_update_optimization() {
        log.info("开始测试库存更新优化");
        
        // 测试活动SKU库存更新
        Long sku = 9011L;
        log.info("测试活动SKU库存更新优化，sku: {}", sku);
        activityRepository.updateActivitySkuStock(sku);
        
        // 测试策略奖品库存更新
        Long strategyId = 100006L;
        Integer awardId = 101;
        log.info("测试策略奖品库存更新优化，strategyId: {}, awardId: {}", strategyId, awardId);
        strategyRepository.updateStrategyAwardStock(strategyId, awardId);
    }
}
