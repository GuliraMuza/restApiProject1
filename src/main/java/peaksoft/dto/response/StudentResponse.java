package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.StudyFormat;

@Builder
public record StudentResponse(
        Long id,
        String firsName,
        String lastName,
        String phoneNumber,
        String email,
        StudyFormat studyFormat) {
}
