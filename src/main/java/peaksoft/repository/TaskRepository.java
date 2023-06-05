package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT new peaksoft.dto.response.TaskResponse(t.id,t.taskName,t.taskText,t.deadLine) FROM Task t")
    List<TaskResponse> getAllTasks();


    Optional<TaskResponse> getTaskById(Long taskId);
}
