package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {




    @Query("SELECT new peaksoft.dto.response.CompanyResponse(c.id, c.name, c.country, c.address, c.phoneNumber) FROM Company c")
    List<CompanyResponse> getAllCompanies();


    Optional<CompanyResponse> getCompanyById(Long companyId);


    @Modifying
    @Query("UPDATE Company s SET s.name = :name, s.country = :country, s.address = :address,s.phoneNumber = :phoneNumber WHERE s.id = :id")
    void updateCompany(@Param("id") Long id, @Param("name") String name, @Param("country") String country, @Param("address") String address,@Param("phoneNumber") String phoneNumber);



    @Query("select count (s)from Company c join Instructor i join i.courses co join co.groups g join Student s where c.id=:companyId")
    int getAllStudentSize(Long companyId);


    @Query("select i.firstName from Company com join Instructor i  where com.id=:companyId")

    List<String> getAllInstructorName(Long companyId);
    @Query("select g.groupName from Company com  join Course c join Group g where com.id=:companyId")
    List<String> getAllGroupName(Long companyId);
    @Query("select c.courseName from Company com  join Course c  where com.id=:companyId")
    List<String> getAllCourseName(Long companyId);
}


