package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
//@Slf4j // логирование только бэкке керек  Simple Logging  Facade  for Java
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest, Long courseId) {
        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NoSuchElementException("Lesson with id: " + courseId + " not found"));
            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonRequest.getLessonName());
            lesson.setDescription(lessonRequest.getDescription());
            lesson.setCourse(course);
            lessonRepository.save(lesson);

            return LessonResponse.builder()
                    .id(lesson.getId())
                    .lessonName(lessonRequest.getLessonName())
                    .description(lessonRequest.getDescription())
                    .build();
        } catch (Exception e) {
            throw new NoSuchElementException("Lesson with id:" + courseId + " is not found");
        }
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public LessonResponse getLessonById(Long lessonId) {

        return lessonRepository.getLessonById(lessonId).orElseThrow(() ->{
           // log.error("Lesson doesn't exist");
        throw  new NotFoundException("Lesson with id: " + lessonId + " not found!");
        });

    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson=lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Course with id: " + lessonId + "not found!"));
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setDescription(lessonRequest.getDescription());
       // log.info("Updated");
        lessonRepository.save(lesson);
        return LessonResponse.builder()
                .id(lesson.getId())
                .lessonName(lessonRequest.getLessonName())
                .description(lessonRequest.getDescription())
                .build();
    }

    @Override
    public SimpleResponse deleteLesson(Long lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new NotFoundException("Lesson with id: " + lessonId + " not found!"));
            Course course = lesson.getCourse();
            if (course != null) {
                course.getLessons().remove(course);
            }
            lessonRepository.delete(lesson);

            return SimpleResponse.builder()
                    .status(200)
                    .message(String.format("Course with id : %d successfully deleted!", lessonId))
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to delete course: " + e.getMessage())
                    .build();
        }
    }

    }
