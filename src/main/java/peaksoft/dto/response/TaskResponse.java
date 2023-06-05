package peaksoft.dto.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record TaskResponse(
        Long id,
        String taskName,
        String taskText,
        LocalDate deadLine) {
    public TaskResponse(Long id, String taskName, String taskText, LocalDate deadLine) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
