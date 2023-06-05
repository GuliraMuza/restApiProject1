package peaksoft.dto.response.simple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRe {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private List<String> courseName;
    private List<String> groupName;
    private List<String> instructorName;
    private int studentCount;


}
