package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }


    @PostMapping("/{companyId}")
    public ResponseEntity<SimpleResponse> saveCourse(@PathVariable Long companyId, @RequestBody @Valid CourseRequest courseRequest) {
        SimpleResponse response = courseService.saveCourse(companyId, courseRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    @GetMapping("/{courseId}")
    public ResponseEntity<SimpleResponse> getCourseById(@PathVariable Long courseId){
        SimpleResponse response = courseService.getCourseById(courseId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,@RequestBody @Valid CourseRequest courseRequest){
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{courseId}")
    public SimpleResponse deleteById(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping("/{courseId}/{instructorId}")
    public SimpleResponse assignCourseToInstructor (@PathVariable Long courseId,@PathVariable  Long instructorId){
        return courseService.assignInstructorToCourse(courseId,instructorId);
    }



    @GetMapping("/sort")
    public List<CourseResponse> sortByCourseDateOfStartAscOrDesc(@RequestParam(required = false,defaultValue = "asc") String ascOrDesc){
        return courseService.sortByCourseDateOfStartAscOrDesc(ascOrDesc);
    }

   /* @PostMapping("/{courseId}")
    public SimpleResponse dateOfStartAscOrDesc(@PathVariable  Long  courseId , @RequestParam LocalDate dateOfStart){
        return courseService.dateOfStartUpdate(courseId,dateOfStart);
    }*/

}
