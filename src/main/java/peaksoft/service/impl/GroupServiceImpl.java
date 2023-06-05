package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        course.getGroups().add(group);
        groupRepository.save(group);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
    }


    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroups();

    }

    @Override
    public GroupResponse getGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Group with id:" + groupId + " doesn't exist"));
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDescription(),
                group.getImageLink()
        );
    }


    @Override
    public GroupResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group with id:" + groupId + " not found"));
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        groupRepository.save(group);
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(groupRequest.getGroupName())
                .description(groupRequest.getDescription())
                .imageLink(groupRequest.getImageLink())
                .build();
    }


    @Override
    public SimpleResponse deleteGroup(Long groupId) {
        try {
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new NotFoundException("Group with id: " + groupId + " not found!"));
            for (Course course : group.getCourses()) {
                if (course != null) {
                    course.getGroups().remove(group);
                }
                groupRepository.delete(group);
            }
            return SimpleResponse.builder()
                    .status(200)
                    .message(String.format("Group with id : %d successfully deleted!", groupId))
                    .build();


        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(500)
                    .message("Failed to delete course: " + e.getMessage())
                    .build();
        }
    }


}





























