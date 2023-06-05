package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.CompanyRe;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface CompanyService {

    SimpleResponse saveCompany(CompanyRequest companyRequest);
    List<CompanyResponse>getAllCompanies();


    CompanyResponse getCompanyById(Long companyId);

    SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest);

    SimpleResponse deleteCompanyById(Long companyId);

    CompanyRe infoCompany(Long companyId);
}
