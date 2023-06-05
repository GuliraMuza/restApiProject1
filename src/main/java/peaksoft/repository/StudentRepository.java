package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id,s.firsName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked) FROM Student  s")
    List<StudentResponse> getAllStudents();

    Optional<StudentResponse>getStudentsById(Long studentId);


    List<StudentResponse> findByIsBlocked(Boolean isBlocked);


    List<StudentResponse>findByStudyFormat(StudyFormat studyFormat);
}
