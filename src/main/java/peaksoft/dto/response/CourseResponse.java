package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record CourseResponse(
        Long id,
        String courseName,

        String description) {
    public CourseResponse(Long id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }
}
