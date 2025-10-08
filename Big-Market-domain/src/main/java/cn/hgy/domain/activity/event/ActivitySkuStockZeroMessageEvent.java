package cn.hgy.domain.activity.event;

import cn.hgy.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-20
 * @Description: 活动sku库存清空消息
 */
@Component
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long>{
    @Value("${spring.rabbitmq.listener.topic.activity_sku_stock_zero}")
    private String topic;

    @Override
    public BaseEvent.EventMessage<Long> buildEventMessage(Long sku) {
        return BaseEvent.EventMessage.<Long>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(sku)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
}
