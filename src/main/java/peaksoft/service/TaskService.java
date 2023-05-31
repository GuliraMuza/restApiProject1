package peaksoft.service;

import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    TaskResponse save(Task task, Long lessonId);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long taskId);
    TaskResponse updateTask(Long taskId);
    SimpleResponse deleteTask(Long taskId);
}
