package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping
    public List< TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/{lessonId}")
    public TaskResponse saveTasks(@PathVariable Long lessonId , @RequestBody @Valid TaskRequest taskRequest) {
        return taskService.save(taskRequest,lessonId);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @DeleteMapping("/{taskId}")
    public SimpleResponse deleteTaskById(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest taskRequest){
        return taskService.updateTask(id,taskRequest);
    }
}
