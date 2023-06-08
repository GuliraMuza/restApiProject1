package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public TaskResponse save(TaskRequest taskRequest, Long lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new NoSuchElementException("Lesson with id: " + lessonId + " not found"));
            Task task1 = new Task();
            task1.setTaskName(taskRequest.getTaskName());
            task1.setTaskText(taskRequest.getTaskText());
            task1.setDeadLine(taskRequest.getDeadLine());
            task1.setLesson(lesson);
            lesson.getTasks().add(task1);
            taskRepository.save(task1);
            return TaskResponse.builder()
                    .id(task1.getId())
                    .taskName(taskRequest.getTaskName())
                    .taskText(taskRequest.getTaskText())
                    .deadLine(taskRequest.getDeadLine())
                    .build();
        } catch (Exception e) {
            throw new NotFoundException("Task with id:" + lessonId + " is not found");
        }

    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId).orElseThrow(() -> new RuntimeException("Task with id: " + taskId + " not found!"));

    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task=taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task with id: " + taskId + "not found!"));
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadLine(LocalDate.now());
        taskRepository.save(task);
        return TaskResponse.builder()
                .id(task.getId())
                .taskName((taskRequest.getTaskName()))
                .taskText(taskRequest.getTaskText())
                .deadLine(taskRequest.getDeadLine())
                .build();
    }


    @Override
    public SimpleResponse deleteTask(Long taskId) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Course with id: " + taskId + " not found!"));

           Lesson lesson = task.getLesson();
            if (lesson != null) {
                lesson.getTasks().remove(task);
            }
            taskRepository.delete(task);
            return SimpleResponse.builder()
                    .status(200)
                    .message(String.format("Task with id : %d successfully deleted!", taskId))
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to delete task: " + e.getMessage())
                    .build();
        }
    }
}
