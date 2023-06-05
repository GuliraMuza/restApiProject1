package peaksoft.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@Getter
@Setter

public class StudentRequest {
    @NotEmpty(message = "FirstName should not be empty")
    @Size(min = 2,max = 30,message = "FirstName should be between 2 and 30 characters")
    private String firsName;

    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2,max = 30,message = "LastName should be between 2 and 30 characters")
    private String lastName;

    @Pattern(regexp = "^\\+996\\d{9}$", message = "Phone number must start with +996 and contain 12 digits")
    private String phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    @Column(name = "email",unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    public StudentRequest(String firsName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
