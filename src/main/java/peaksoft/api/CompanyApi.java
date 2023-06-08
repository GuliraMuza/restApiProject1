package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.CompanyRe;
import peaksoft.dto.response.simple.InstructorRe;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CompanyApi {
    private final CompanyService companyService;




        @PostMapping
        public ResponseEntity<SimpleResponse> saveCompany( @RequestBody @Valid  CompanyRequest companyRequest) {
            SimpleResponse response = companyService.saveCompany(companyRequest);
            return ResponseEntity.ok(response);
        }

       // @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
        @GetMapping
        public List<CompanyResponse> getAllCompanies(){
            return companyService.getAllCompanies();
        }


    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id){
        return  companyService.getCompanyById(id);
    }


   // @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{id}")
    public SimpleResponse updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyRequest companyRequest){
        return companyService.updateCompany(id, companyRequest);
    }

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{companyId}")
    public SimpleResponse deleteCompanyById(@PathVariable Long companyId) {
        return companyService.deleteCompanyById(companyId);
    }


   // @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/info/{companyId}")
    public CompanyRe companyRe (@PathVariable Long companyId){
            return companyService.infoCompany(companyId);
    }
}
