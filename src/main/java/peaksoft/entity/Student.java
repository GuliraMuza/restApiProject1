package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

import static jakarta.persistence.CascadeType.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String firsName;
    private String lastName;
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    @Column(name = "email",unique = true)
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Boolean isBlocked;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE})
    private Group group;
}