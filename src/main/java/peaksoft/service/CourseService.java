package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest);



    List<CourseResponse> getAllCourses();



    SimpleResponse getCourseById(Long courseId);

    CourseResponse updateCourse(Long courseId, CourseRequest courseRequest);

    SimpleResponse deleteCourseById(Long courseId);


    SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId);

    List<CourseResponse> sortByCourseDateOfStartAscOrDesc(String ascOrDesc);

   // SimpleResponse dateOfStartUpdate(Long studentId, LocalDate dateOfStar);
}
