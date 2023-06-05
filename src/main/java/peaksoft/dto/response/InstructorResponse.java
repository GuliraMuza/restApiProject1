package peaksoft.dto.response;

import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
public record InstructorResponse(Long id,
                                 String firstName,
                                 String lastName,
                                 String phoneNumber,
                                 String specialization) {
    public InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
