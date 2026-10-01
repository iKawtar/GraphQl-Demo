package ma.xproce.videoservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatorRequest {
    private String name;
    private String email;
}
