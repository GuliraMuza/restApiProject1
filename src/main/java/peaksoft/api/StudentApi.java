package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return  studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudents(@PathVariable Long id, @RequestBody @Valid StudentRequest studentRequest){
        return studentService.updateStudent(id,studentRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/{studentId}/{groupId}")
    public SimpleResponse assignStudentToGroup (@PathVariable Long studentId,@PathVariable Long groupId){
        return studentService.assignStudentsToGroup(studentId,groupId);
    }



    @GetMapping("/block")
    public List<StudentResponse>getStudentsByBlockedOrNot(@RequestParam (required = false) Boolean isBlocked){
        return studentService.getStudentsByIsBlockedOrNot(isBlocked);
    }

    @PostMapping("/{studentId}")
    public SimpleResponse blockUnBlockStudent( @PathVariable  Long  studentId ,@RequestParam  Boolean block){
        return studentService.isBlockedUnBlockStudents(studentId,block);
    }

    @GetMapping("/filter")
    public  List<StudentResponse>filterStudent(@RequestParam (required = false) StudyFormat studyFormat){
        return studentService.filterStudents(studyFormat);
    }





}
