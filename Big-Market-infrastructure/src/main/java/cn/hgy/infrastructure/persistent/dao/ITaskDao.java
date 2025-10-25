package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.Task;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-30
 * @Description: 任务表，发送MQ
 */
public interface ITaskDao {
    void insert(Task task);

    void updateTaskSendMessageCompleted(Task task);

    void updateTaskSendMessageFail(Task task);

    List<Task> queryNoSendMessageTaskList();
}
