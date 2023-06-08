package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;
    @Override
    public SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new NoSuchElementException("Company with id: " + companyId + " not found"));
            Course course = new Course();
            course.setCourseName(courseRequest.getCourseName());
            course.setDateOfStart(courseRequest.getDateOfStart());
            course.setDescription(courseRequest.getDescription());
            company.getCourses().add(course);
            course.setCompany(company);
            courseRepository.save(course);

            return SimpleResponse.builder()
                    .status(200)
                    .message("Course saved successfully!")
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to save course: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.getAllCourses();

    }



    @Override
    public SimpleResponse getCourseById(Long courseId) {
        try{
            courseRepository.getCourseById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with id : " + courseId+ " doesn't exist"));

            return SimpleResponse.builder()
                    .status(200)
                    .message("Course " +courseId+" successfully!")
                    .build();
    }
        catch (Exception e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to get course: " + e.getMessage())
                    .build();
        }
    }



    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new NotFoundException("Course with id : " + courseId + " doesn't exist"));
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setDateOfStart(courseRequest.getDateOfStart());
        courseRepository.save(course);
        return  CourseResponse.builder()
                .id(course.getId())
                .courseName(courseRequest.getCourseName())
                .description(courseRequest.getDescription())
                .dateOfStart(courseRequest.getDateOfStart())
                .build();


    }

    @Override
    public SimpleResponse deleteCourseById(Long courseId) {
        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NotFoundException("Course with id: " + courseId + " not found!"));

            Company company = course.getCompany();
            if (company != null) {
                company.getCourses().remove(course);
            }

            courseRepository.delete(course);

            return SimpleResponse.builder()
                    .status(200)
                    .message(String.format("Course with id : %d successfully deleted!", courseId))
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to delete course: " + e.getMessage())
                    .build();
        }
    }


    @Override
    public SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId) {
        try{
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NotFoundException("Instructor with id:" + instructorId + " is not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id:" + courseId + " is not found"));
        course.setInstructor(instructor);
        instructor.getCourses().add(course);
        courseRepository.save(course);
            return SimpleResponse.builder()
                    .status(200)
                    .message(String.format("Course with id : %d successfully  assign!", courseId))
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to assign  instructor: " + e.getMessage())
                    .build();
        }
    }



    @Override
    public List<CourseResponse> sortByCourseDateOfStartAscOrDesc(String ascOrDesc) {
        if (ascOrDesc.equals("asc")) {
            return courseRepository.sortByCourseDateOfStartAsc();
        }
        if (ascOrDesc.equals("desc")) {
            return courseRepository.sortByCourseDateOfStartDesc();
        } else {
            return courseRepository.getAllCourses();
        }
    }

    }




