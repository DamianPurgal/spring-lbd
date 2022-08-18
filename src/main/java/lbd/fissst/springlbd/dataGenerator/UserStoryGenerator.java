package lbd.fissst.springlbd.dataGenerator;

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

    private UserStoryRepository userStoryRepository;

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
                    .points(random.nextInt(10) + 1)
                    .status(getRandomUserStoryStatus())
                    .build()
            );
        }
        userStoryRepository.saveAll(userStories);
    }

    private String getRandomString(){

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        int length = random.nextInt(6) + 5;

        for(int i = 0; i < length; i++){
            stringBuilder.append(alphabet.charAt(i));
        }
        return stringBuilder.toString();
    }

    private UserStoryStatus getRandomUserStoryStatus(){
        Random random = new Random();
        return UserStoryStatus.values()[
                random.nextInt(
                        UserStoryStatus.values().length
                )];
    }
}
