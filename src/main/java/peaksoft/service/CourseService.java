package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(CourseRequest courseRequest, Long companyId);
    List<CourseResponse> getAllCourses();

    CourseResponse getCourseByById(Long courseId);

    CourseResponse updateCourse(Long courseId, CourseRequest courseRequest);

    SimpleResponse deleteCompanyById(Long courseId);
}
