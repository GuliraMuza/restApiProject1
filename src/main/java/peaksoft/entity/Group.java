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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = "group_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen",sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    @ManyToMany(mappedBy = "groups",cascade = {REFRESH,DETACH,MERGE})
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = {DETACH,MERGE,REFRESH,REMOVE})
    private List<Student> students = new ArrayList<>();
}
