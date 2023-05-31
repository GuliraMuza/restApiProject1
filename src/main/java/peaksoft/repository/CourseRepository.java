package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
