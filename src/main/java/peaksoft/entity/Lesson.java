package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    private String lessonName;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE})
    private Course course;

    @OneToMany(mappedBy = "lesson",cascade = {DETACH,REFRESH,MERGE,REMOVE})
    private List<Task> tasks = new ArrayList<>();
}