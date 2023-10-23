package com.osroyale.game.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TaskDeadlockWatcher implements Runnable {
    private boolean exitRequested;
    private final TaskManager taskManager;
    private static final long DEADLOCK_WATCHER_SLEEP_MS = 1000;
    private static final long TASK_TOO_LONG_MS = 1000;
    private static final Logger logger = LogManager.getLogger();

    /**
     * A map of tasks that have already alerted us to a deadlock, we don't want to spam the logs
     */
    private final HashMap<String, Task> _alreadyAlertedTaskMap = new HashMap<>();

    public TaskDeadlockWatcher(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void requestExit() {
        logger.info("TaskDeadlockWatcher exit requested");
        exitRequested = true;
    }

    @Override

    public void run() {
        while (!exitRequested) {
            try {
                var tasks = taskManager.getTasks().stream().collect(Collectors.toList()).stream()
                        .filter(t -> t != null && t.getRunStartTime().isPresent())
                        .sorted(Comparator.comparingLong((Task t) -> t.getTaskCreationTime()).reversed())
                        .collect(Collectors.toList());

                for (int i = 0; i < tasks.size(); i++) {
                    var task = tasks.get(i);
                    if (task == null || _alreadyAlertedTaskMap.containsKey(task.getTaskId())) {
                        continue;
                    }

                    if (task.getElapsedTimeFromRunStartTime() > TASK_TOO_LONG_MS) {
                        var elapsedTime = task.getElapsedTimeFromRunStartTime();
                        logger.error("Task {} deadlock detected, task has been running for {}ms, task: {}, stacktrace: {}", task.getTaskId(), elapsedTime, task, task.getCreationStackTraceStr());
                        _alreadyAlertedTaskMap.put(task.getTaskId(), task);
                    }
                }

                Thread.sleep(DEADLOCK_WATCHER_SLEEP_MS);
            } catch (Exception ex) {
                logger.error("Error in TaskDeadlockWatcher", ex);
            }
        }
    }
}
