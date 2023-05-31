package peaksoft.service;

import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface LessonService {
    LessonResponse save(LessonRequest lessonRequest,Long courseId);
    List<LessonResponse> getAllLessons();
    LessonResponse getLessonById(Long lessonId);
    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);
    SimpleResponse deleteLesson(Long lessonId);
}
