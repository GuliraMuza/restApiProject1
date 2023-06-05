package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return  groupService.getAllGroups();
    }




    @PostMapping("/{courseId}")
    public GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody  @Valid  GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }

    @GetMapping("/{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }


    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody @Valid GroupRequest groupRequest){
        return groupService.updateGroup(id,groupRequest);
    }



    @DeleteMapping("/{groupId}")
    public SimpleResponse deleteGroupById(@PathVariable Long groupId) {
        return groupService.deleteGroup(groupId);
    }

}
