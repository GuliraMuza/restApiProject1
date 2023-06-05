package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Course;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query("SELECT new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.description) FROM Course c")
    List<CourseResponse> getAllCourses();


    Optional<CourseResponse> getCourseById(Long courseId);



    @Modifying
    @Query("UPDATE Course c SET c.courseName = :name,  c.description = :description WHERE c.id = :id")
    void updateCourse(@Param("id") Long id, @Param("name") String name, @Param("description") String description);


    @Query("select new peaksoft.dto.response.CourseResponse (c.id,c.courseName,c.description)from Course c order by c.dateOfStart asc ")
    List<CourseResponse> sortByCourseDateOfStartAsc();
    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.description) from Course c order by c.dateOfStart desc")
    List<CourseResponse> sortByCourseDateOfStartDesc();

}
