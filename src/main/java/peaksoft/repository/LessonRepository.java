package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    @Query("SELECT new peaksoft.dto.response.LessonResponse(l.id,l.lessonName,l.description) FROM Lesson  l")
    List<LessonResponse> getAllLessons();

    Optional<LessonResponse>getLessonById(Long lessonId);
}
