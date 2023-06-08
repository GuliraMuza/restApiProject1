package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "company_gen")
    @SequenceGenerator(name = "company_gen",sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "company",cascade = {ALL},fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE,PERSIST})
    private List<Instructor> instructors = new ArrayList<>();
}
















































