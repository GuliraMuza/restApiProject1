package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
