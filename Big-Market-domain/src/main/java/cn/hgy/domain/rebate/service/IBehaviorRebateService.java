package cn.hgy.domain.rebate.service;

import cn.hgy.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-26
 * @Description: 行为返利服务接口
 */
public interface IBehaviorRebateService {
    /**
     * 创建行为动作的入账订单
     *
     * @param behaviorEntity 行为实体对象
     * @return 订单ID
     */
    List<String> createOrder(BehaviorEntity behaviorEntity);
}
