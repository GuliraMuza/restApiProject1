package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.CompanyRe;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {



    private final CompanyRepository companyRepository;


    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        log.info("New company successfully saved!");
        return  SimpleResponse.builder().status(200).message("Saved Company").build();
    }


    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }


    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return companyRepository.getCompanyById(companyId) .orElseThrow(() -> new NotFoundException("Company with id:" + companyId + "is not found"));
    }



    @Override
    public SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company1 = companyRepository.findById(companyId).orElseThrow(() ->
                new RuntimeException("Company with id : " + companyId + " doesn't exist"));
        company1.setName(companyRequest.getName());
        company1.setCountry(companyRequest.getCountry());
        company1.setAddress(companyRequest.getAddress());
        company1.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company1);
        return SimpleResponse.builder()
                .status(200)
                .message("Company successfully updated ...!")
                .build();
    }


    @Override
    public SimpleResponse deleteCompanyById(Long companyId) {
        companyRepository.deleteById(companyId);
        return new SimpleResponse(200, "Company deleted successfully!");
    }


    @Override
    public CompanyRe infoCompany(Long companyId) {
        CompanyResponse c = companyRepository.getCompanyById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id:" + companyId + "is not found"));
        if (companyId!=null && companyId.equals(c.getId())) {

            return CompanyRe.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .address(c.getAddress())
                    .country(c.getCountry())
                    .phoneNumber(c.getPhoneNumber())
                    .courseName(companyRepository.getAllCourseName(companyId))
                    .groupName(companyRepository.getAllGroupName(companyId))
                    .studentCount(companyRepository.getAllStudentSize(companyId))
                    .instructorName(companyRepository.getAllInstructorName(companyId))
                    .build();


        }
        return null;
    }


    }
