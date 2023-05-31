package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_gen")
    @SequenceGenerator(name = "course_gen",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    private Company company;

    @OneToMany(mappedBy = "course",cascade = {DETACH,REFRESH,MERGE})
    private List<Instructor> instructors = new ArrayList<>();

    @ManyToMany(cascade = {REFRESH,DETACH,MERGE})
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = {REFRESH,DETACH,MERGE,REMOVE})
    private List<Lesson> lessons = new ArrayList<>();
}
