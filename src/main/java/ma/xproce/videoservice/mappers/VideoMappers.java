package ma.xproce.videoservice.mappers;


import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dto.CreatorRequest;
import ma.xproce.videoservice.dto.VideoRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMappers {

    private ModelMapper modelMapper = new ModelMapper();

    public Video fromVideoRequestToVideo(VideoRequest videoRequest) {
        return modelMapper.map(videoRequest, Video.class);
    }

    public Creator fromCreatorRequestToCreator(CreatorRequest creatorRequest) {
        return modelMapper.map(creatorRequest, Creator.class);
    }
}