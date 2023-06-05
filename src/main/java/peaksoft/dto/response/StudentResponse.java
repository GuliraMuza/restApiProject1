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
        StudyFormat studyFormat,
        Boolean isBlocked) {
    public StudentResponse(Long id, String firsName, String lastName, String phoneNumber, String email, StudyFormat studyFormat, Boolean isBlocked) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.isBlocked = isBlocked;
    }
}
