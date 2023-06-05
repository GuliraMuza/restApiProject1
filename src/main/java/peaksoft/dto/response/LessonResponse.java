package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record LessonResponse(
        Long id,
        String lessonName,
        String description) {
    public LessonResponse(Long id, String lessonName, String description) {
        this.id = id;
        this.lessonName = lessonName;
        this.description = description;
    }
}
