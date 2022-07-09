package lbd.fissst.springlbd.service.dataGenerator;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserStoryGenerator {

    UserStoryRepository userStoryRepository;

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Transactional
    @PostConstruct
    public void generateHundredUserStories(){

        Random random = new Random();

        List<UserStory> userStories = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            userStories.add(UserStory.builder()
                    .name(getRandomString())
                    .description(getRandomString())
                    .points(random.nextInt(10) + 5)
                    .status(getRandomUserStoryStatus())
                    .build()
            );
        }
        userStoryRepository.saveAll(userStories);
    }

    private String getRandomString(){

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        int length = random.nextInt(4) + 3;

        for(int i = 0; i < length; i++){
            stringBuilder.append(alphabet.charAt(i));
        }
        return stringBuilder.toString();
    }

    private UserStoryStatus getRandomUserStoryStatus(){

        Random random = new Random();

        return switch (random.nextInt(UserStoryStatus.values().length)) {
            case 0 -> UserStoryStatus.TO_DO;
            case 1 -> UserStoryStatus.IN_PROGRESS;
            case 2 -> UserStoryStatus.REVIEW;
            case 3 -> UserStoryStatus.DONE;
            default -> UserStoryStatus.TO_DO;
        };
    }


}
