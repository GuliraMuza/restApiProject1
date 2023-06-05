package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT new peaksoft.dto.response.GroupResponse(g.id, g.groupName,g.imageLink, g.description) FROM Group  g")
    List<GroupResponse> getAllGroups();

    Optional<GroupResponse> getGroupById(Long groupId);

}
