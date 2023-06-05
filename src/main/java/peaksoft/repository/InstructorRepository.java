package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import peaksoft.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    @Query("SELECT new peaksoft.dto.response.InstructorResponse(i.id, i.firstName,i.lastName, i.phoneNumber,i.specialization) FROM Instructor i")
    List<InstructorResponse> getAllInstructors();


    Optional<InstructorResponse> getInstructorById(Long instructorId);

    @Modifying
    @Query("DELETE FROM Instructor i WHERE i.id = :id")
    void deleteInstructorById(@Param("id") Long id);




    @Query("select count(s) from Instructor i join i.courses c join  c.groups g join g.students s where i.id=:instructorId")
    int getAllStudentSize(Long instructorId);
    @Query("select g.groupName from Instructor i join i.courses c join  c.groups g where i.id =:instructorId")
    List<String> getAllGroupName(Long instructorId);
}
