package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface GroupService {


    GroupResponse saveGroup(Long courseId, GroupRequest groupRequest);

    List<GroupResponse> getAllGroups();
    GroupResponse getGroupById(Long groupId);
    GroupResponse updateGroup(Long groupId, GroupRequest groupRequest);
    SimpleResponse deleteGroup(Long groupId);

}
