package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;



    /*  String email = request.getEmail();
        if (userRepository.existsByEmail((email))) {
            log.error("there is such a student with email :{}", email);
            throw new BadRequestException(String.format("There is such a student with email = %s", email));
        }*/

    @Override
    public StudentResponse save(StudentRequest studentRequest) {
        String name=studentRequest.getFirsName();

        Student student = new Student();
        student.setFirsName(studentRequest.getFirsName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setIsBlocked(false);
        studentRepository.save(student);

        return new StudentResponse(
                student.getId(),
                student.getFirsName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getStudyFormat(),
                student.getIsBlocked()
        );
    }


    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        return studentRepository.getStudentsById(studentId).orElseThrow(() -> new NoSuchElementException("Student with id:" + studentId + " is not found"));
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id : " + studentId + " doesn't exist"));
        student.setFirsName(studentRequest.getFirsName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setIsBlocked(false);
        studentRepository.save(student);
        return StudentResponse.builder()
                .id(student.getId())
                .firsName(studentRequest.getFirsName())
                .lastName(studentRequest.getLastName())
                .phoneNumber(studentRequest.getPhoneNumber())
                .email(studentRequest.getEmail())
                .studyFormat(studentRequest.getStudyFormat())
                .build();
    }

    @Override
    public SimpleResponse deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
        return new SimpleResponse(200, "Student deleted successfully!");
    }
  

    @Override
    public SimpleResponse assignStudentsToGroup(Long studentId, Long groupId) {
        try{
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id : " + studentId + " doesn't exist"));
        Group group= groupRepository.findById(groupId).orElseThrow(() ->
                new RuntimeException("Student with id : " +groupId + " doesn't exist"));
        group.getStudents().add(student);
        student.setGroup(group);
          studentRepository.save(student);
        return SimpleResponse.builder()
                .status(200)
                .message(String.format("Student with id : %d successfully  assign!", studentId))
                .build();
    } catch (RuntimeException e) {
        return SimpleResponse.builder()
                .status(500)
                .message("Failed to delete student: " + e.getMessage())
                .build();
    }}


    @Override
    public List<StudentResponse>getStudentsByIsBlockedOrNot(Boolean isBlocked){
        return studentRepository.findByIsBlocked(isBlocked);
    }


    @Override
    public List<StudentResponse>filterStudents(StudyFormat studyFormat) {
        return studentRepository.findByStudyFormat(studyFormat);

   }

    @Override
    public SimpleResponse isBlockedUnBlockStudents(Long studentId, Boolean block) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id : " + studentId + " doesn't exist"));
        student.setIsBlocked(block);
        studentRepository.save(student);
        return new SimpleResponse(200,"Student with id:"+studentId+ "is blocked");
    }

}