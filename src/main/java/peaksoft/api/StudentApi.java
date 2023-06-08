package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;



@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return  studentService.getStudentById(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{id}")
    public StudentResponse updateStudents(@PathVariable Long id, @RequestBody @Valid StudentRequest studentRequest){
        return studentService.updateStudent(id,studentRequest);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/{studentId}/{groupId}")
    public SimpleResponse assignStudentToGroup (@PathVariable Long studentId ,@PathVariable Long groupId){
        return studentService.assignStudentsToGroup(studentId,groupId);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/block")
    public List<StudentResponse>getStudentsByBlockedOrNot(@RequestParam (required = false) Boolean isBlocked){
        return studentService.getStudentsByIsBlockedOrNot(isBlocked);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/{studentId}")
    public SimpleResponse blockUnBlockStudent( @PathVariable  Long  studentId ,@RequestParam  Boolean block){
        return studentService.isBlockedUnBlockStudents(studentId,block);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/filter")
    public  List<StudentResponse>filterStudent(@RequestParam (required = false) StudyFormat studyFormat){
        return studentService.filterStudents(studyFormat);
    }





}
