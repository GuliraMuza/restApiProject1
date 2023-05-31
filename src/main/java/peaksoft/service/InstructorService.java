package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long instructorId);
    InstructorResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);
    SimpleResponse deleteInstructor(Long instructorId);
    SimpleResponse assignInstructorsToCompany(Long instructorId,List<Long>companyIdes);

}

