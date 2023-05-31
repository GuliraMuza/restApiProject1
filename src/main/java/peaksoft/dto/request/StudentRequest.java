package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@Getter
@Setter
@AllArgsConstructor
public class StudentRequest {
    private String firsName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
}
