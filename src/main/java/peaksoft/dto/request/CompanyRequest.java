package peaksoft.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

}
