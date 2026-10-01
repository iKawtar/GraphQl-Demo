package ma.xproce.videoservice;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository){
        return args -> {
            List<Creator> creators = List.of(Creator.builder().name("Kawtar")
                    .email("kawtar@gmail.com").build(), Creator.builder().name("Lina")
                    .email("lina@gmail.com").build(), Creator.builder().name("Sofia")
                    .email("sofia@gmail.com").build());
            creatorRepository.saveAll(creators);
            List<Video> videos = List.of(Video.builder().name("SystemDesign")
                    .url("SystemDesign.com").datePublication(new Date())
                    .description("System Design Video").creator(creators.get(1))
                    .build(), Video.builder().name("MlOps").url("MlOps.com")
                    .datePublication(new Date()).description("MlOps Video")
                    .creator(creators.get(0)).build(), Video.builder().name("SpringBoot")
                    .url("SpringBoot.com").datePublication(new Date())
                    .description("SpringBoot Video")
                    .creator(creators.get(2)).build());
            videoRepository.saveAll(videos);
        };
    }
}
