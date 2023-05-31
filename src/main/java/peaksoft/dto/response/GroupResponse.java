package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record GroupResponse(Long id,
                            String groupName,
                            String imageLink,
                            String description) {
}
