package ma.xproce.videoservice.web;

import ma.xproce.videoservice.dto.CreatorRequest;
import ma.xproce.videoservice.dto.VideoRequest;
import ma.xproce.videoservice.mappers.VideoMappers;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dao.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import ma.xproce.videoservice.services.CreatorManager;
import ma.xproce.videoservice.services.VideoManager;

@Controller

public class VideoGraphQlController {

    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private VideoMappers videoMappers;
    private CreatorManager creatorManager;
    private VideoManager videoManager;


    public VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository, VideoMappers videoMappers, CreatorManager creatorManager, VideoManager videoManager) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.videoMappers = videoMappers;
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<Video> videoList(){
        return videoRepository.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id){
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found",id)));
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator){
        Creator creatorEntity = videoMappers
                .fromCreatorRequestToCreator(creator);

        return creatorRepository.save(creatorEntity);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video) {

        Creator creator = videoMappers
                .fromCreatorRequestToCreator(video.getCreator());

        creatorRepository.save(creator);

        Video videoEntity =
                videoMappers.fromVideoRequestToVideo(video);

        videoEntity.setCreator(creator);

        return videoRepository.save(videoEntity);
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" +
                                    new Random().nextInt())
                            .email("x@gmail.com").build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }
}
