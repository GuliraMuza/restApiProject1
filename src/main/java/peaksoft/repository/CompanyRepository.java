package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
