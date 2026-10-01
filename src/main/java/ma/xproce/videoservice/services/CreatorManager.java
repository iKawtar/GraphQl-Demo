package ma.xproce.videoservice.services;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dto.CreatorRequest;
import ma.xproce.videoservice.mappers.VideoMappers;
import org.springframework.stereotype.Service;

@Service
public class CreatorManager {

    private CreatorRepository creatorRepository;
    private VideoMappers videoMappers;

    public CreatorManager(CreatorRepository creatorRepository, VideoMappers videoMappers) {
        this.creatorRepository=creatorRepository;
        this.videoMappers=videoMappers;
    }

    public Creator saveCreator(CreatorRequest creatorRequest) {
        Creator creator=videoMappers.fromCreatorRequestToCreator(creatorRequest);
        return creatorRepository.save(creator);
    }
}