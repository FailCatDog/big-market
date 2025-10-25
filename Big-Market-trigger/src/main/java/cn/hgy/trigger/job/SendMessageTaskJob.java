package cn.hgy.trigger.job;

import cn.hgy.domain.task.model.entity.TaskEntity;
import cn.hgy.domain.task.service.ITaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-10-08
 * @Description: 发送MQ消息任务队列
 */
@Slf4j
@Component()
@RequiredArgsConstructor
public class SendMessageTaskJob {

    private final ITaskService taskService;
    private final ThreadPoolExecutor executor;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        try {
            List<TaskEntity> taskEntities = taskService.queryNoSendMessageTaskList();
            if (taskEntities.isEmpty()) return;

            for (TaskEntity taskEntity : taskEntities) {
                executor.execute(() -> {
                    try {
                        taskService.sendMessage(taskEntity);
                        taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                    } catch (Exception e) {
                        log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                        taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                    }
                });
            }
        } catch (Exception e) {
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        }
    }
}
