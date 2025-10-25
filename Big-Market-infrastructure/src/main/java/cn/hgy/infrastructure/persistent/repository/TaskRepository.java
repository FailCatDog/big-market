package cn.hgy.infrastructure.persistent.repository;

import cn.hgy.domain.task.model.entity.TaskEntity;
import cn.hgy.domain.task.repository.ITaskRepository;
import cn.hgy.infrastructure.event.EventPublisher;
import cn.hgy.infrastructure.persistent.dao.ITaskDao;
import cn.hgy.infrastructure.persistent.po.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 任务服务仓储实现类
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class TaskRepository implements ITaskRepository {
    private final ITaskDao taskDao;
    private final EventPublisher eventPublisher;

    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        List<Task> tasks = taskDao.queryNoSendMessageTaskList();
        List<TaskEntity> taskEntities = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(task.getUserId());
            taskEntity.setTopic(task.getTopic());
            taskEntity.setMessageId(task.getMessageId());
            taskEntity.setMessage(task.getMessage());
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        eventPublisher.publish(taskEntity.getTopic(), taskEntity.getMessage());
    }

    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageCompleted(taskReq);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageFail(taskReq);
    }
}
