package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.InstructorRe;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long instructorId);
    InstructorResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);
    SimpleResponse deleteInstructor(Long instructorId);
    SimpleResponse assignInstructorToCompany(Long instructorId,Long companyId);

    InstructorRe infoInstructor(Long instructorId);
}

