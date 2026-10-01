package ma.xproce.videoservice.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private Date datePublication;
    private CreatorRequest creator;
}