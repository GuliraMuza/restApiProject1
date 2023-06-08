package peaksoft.api;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping
    public List<LessonResponse>getAllLessons(){
        return lessonService.getAllLessons();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/{courseId}")
    public LessonResponse saveCourse(@PathVariable  Long courseId , @RequestBody @Valid LessonRequest lessonRequest) {
      return   lessonService.saveLesson(lessonRequest,courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/{id}")
    public LessonResponse getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{id}")
    public  LessonResponse updateLesson(@PathVariable Long id, @RequestBody @Valid LessonRequest lessonRequest){
        return lessonService.updateLesson(id,lessonRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/{lessonId}")
    public SimpleResponse deleteLessonById(@PathVariable Long lessonId) {
        return lessonService.deleteLesson(lessonId);
    }
}
