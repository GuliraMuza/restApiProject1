package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record GroupResponse(
                            Long id,
                            String groupName,
                            String imageLink,
                            String description) {
    public GroupResponse(Long id, String groupName, String imageLink, String description) {
        this.id = id;
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
