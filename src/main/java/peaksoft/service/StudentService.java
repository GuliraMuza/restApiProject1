package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    StudentResponse save(StudentRequest studentRequest);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long studentId);
    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);
    SimpleResponse deleteStudent(Long studentId);
    SimpleResponse assignStudentsToGroup(Long studentId,Long groupId);
    List<StudentResponse> filterStudentsByStudyFormat(StudyFormat studyFormat);
}
