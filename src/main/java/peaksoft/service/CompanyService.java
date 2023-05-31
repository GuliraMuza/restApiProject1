package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse saveCompany(CompanyRequest companyRequest);
    List<CompanyResponse>getAllCompanies();

    CompanyResponse getCompanyById(Long companyId);

    CompanyResponse updateCompany

}
