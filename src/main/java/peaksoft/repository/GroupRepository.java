package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
