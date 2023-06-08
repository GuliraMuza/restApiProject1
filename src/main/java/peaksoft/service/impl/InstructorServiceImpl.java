package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simple.InstructorRe;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Instructor;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        try{
            Instructor instructor=new Instructor();
            instructor.setFirstName(instructorRequest.getFirstName());
            instructor.setLastName(instructorRequest.getLastName());
            instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
            instructor.setSpecialization(instructorRequest.getSpecialization());
            instructorRepository.save(instructor);

            return InstructorResponse.builder()
                    .id(instructor.getId())
                    .firstName((instructorRequest.getFirstName()))
                    .lastName(instructorRequest.getLastName())
                    .phoneNumber(instructorRequest.getPhoneNumber())
                    .specialization(instructorRequest.getSpecialization())
                    .build();
        } catch (Exception e) {
            throw new NoSuchElementException("Student with id:"+instructorRequest+" is not found");
        }
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        return  instructorRepository.getInstructorById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with id: " + instructorId + " not found!"));
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor=instructorRepository.findById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with id: " +instructorId + "not found!"));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return InstructorResponse.builder()
                .id(instructor.getId())
                .firstName(instructorRequest.getFirstName())
                .lastName(instructorRequest.getLastName())
                .phoneNumber(instructorRequest.getPhoneNumber())
                .specialization(instructorRequest.getSpecialization())
                .build();
}



    @Override
    public SimpleResponse deleteInstructor(Long instructorId) {
        try{
            Instructor instructor= instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NoSuchElementException("Instructor with id: " + instructorId + " is not found"));
            List<Company>companies=instructor.getCompanies();
            for (Company c : companies) {
                c.getInstructors().remove(instructor);
            }
            instructor.getCompanies().clear();
           instructorRepository.delete(instructor);
        return SimpleResponse.builder()
                .status(200)
                .message(String.format("Instructor with id : %d successfully deleted!", instructorId))
                .build();

    } catch (RuntimeException e) {
        return SimpleResponse.builder()
                .status(500)
                .message("Failed to delete instructor: " + e.getMessage())
                .build();
    }
    }




    @Override
    public SimpleResponse assignInstructorToCompany(Long instructorId,Long companyId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NotFoundException("Instructor with id:" + instructorId + "is not found"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company with id:" + companyId + "is not found"));
        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .status(200)
                .message(String.format("Company with id : "+instructorId+" successfully deleted ...!"))
                .build();
    }

    @Override
    public InstructorRe infoInstructor(Long instructorId) {
        InstructorResponse i = instructorRepository.getInstructorById(instructorId).orElseThrow(()->new NotFoundException("Company with id:" + instructorId + "is not found"));
        if (instructorId!=null && instructorId.equals(i.id())){

        return InstructorRe.builder()
                .id(i.id())
                .firstName(i.firstName())
                .lastName(i.lastName())
                .specialization(i.specialization())
                .phoneNumber(i.phoneNumber())
                .groupName(instructorRepository.getAllGroupName(instructorId))
                .studentCount(instructorRepository.getAllStudentSize(instructorId)).build();
        }

        return null;
    }






}
