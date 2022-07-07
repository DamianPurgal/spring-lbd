package lbd.fissst.springlbd;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.service.UserStoryServiceImpl;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import lbd.fissst.springlbd.service.exception.UserStoryNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class UserStoryServiceImplTests {

    @Autowired
    UserStoryServiceImpl userStoryService;

    @Test
    void givenUserStoryWithNullName_whenSaved_shouldThrowException(){
        UserStory givenUserStory = UserStory.builder()
                        .name(null)
                        .description("description")
                        .points(12)
                        .status(UserStoryStatus.TO_DO)
                        .build();

        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithEmptyName_whenSaved_shouldThrowException(){
        UserStory givenUserStory = UserStory.builder()
                .name("")
                .description("description")
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithNullDescription_whenSaved_shouldThrowException(){
        UserStory givenUserStory = UserStory.builder()
                .name("name")
                .description(null)
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithEmptyDescription_whenSaved_shouldThrowException(){
        UserStory givenUserStory = UserStory.builder()
                .name("name")
                .description("")
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithoutStatus_whenSaved_shouldSaveAndSetToDoStatus(){
        UserStory givenUserStory = UserStory.builder()
                .name("name")
                .description("description")
                .points(12)
                .status(null)
                .build();

        UserStory savedUserStory = userStoryService.save(givenUserStory);

        assertEquals(UserStoryStatus.TO_DO, savedUserStory.getStatus());
    }

    @Test
    void givenUserStoryWithStatus_whenSaved_shouldSaveAndShouldNotChangeStatus(){
        UserStory givenUserStory = UserStory.builder()
                .name("name")
                .description("description")
                .points(12)
                .status(UserStoryStatus.DONE)
                .build();

        UserStory savedUserStory = userStoryService.save(givenUserStory);

        assertEquals(UserStoryStatus.DONE, savedUserStory.getStatus());
    }
}
