package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.GroupService;

import java.util.List;
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return  groupService.getAllGroups();
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{courseId}")
    public GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody  @Valid  GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("/{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody @Valid GroupRequest groupRequest){
        return groupService.updateGroup(id,groupRequest);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{groupId}")
    public SimpleResponse deleteGroupById(@PathVariable Long groupId) {
        return groupService.deleteGroup(groupId);
    }

}
