package cn.hgy.domain.task.model.entity;

import lombok.Data;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 任务实体对象
 */
@Data
public class TaskEntity {
    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private String message;
}
