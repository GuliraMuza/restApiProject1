package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.simple.InstructorRe;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.CompanyService;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;
    private final CompanyService companyService;

    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody @Valid InstructorRequest instructorRequest) {
      return   instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }

    @PutMapping("/{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id, @RequestBody @Valid InstructorRequest instructorRequest){
        return instructorService.updateInstructor(id,instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    public SimpleResponse deleteInstructorById(@PathVariable Long instructorId) {
        return instructorService.deleteInstructor(instructorId);
    }


    @PostMapping("/assignCompany/{instructorId}/{companyId}")
    public SimpleResponse assignInstructorToCompany (@PathVariable Long instructorId,@PathVariable Long companyId){
        return instructorService.assignInstructorToCompany(instructorId,companyId);
    }



    @GetMapping("/info/{instructorId}")
    public InstructorRe instructorRe (@PathVariable Long instructorId){
        return instructorService.infoInstructor(instructorId);
    }


}
