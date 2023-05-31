package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
