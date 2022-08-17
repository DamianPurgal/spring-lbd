package lbd.fissst.springlbd.UserStoryServiceTests;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.service.implementation.SprintServiceImpl;
import lbd.fissst.springlbd.service.implementation.UserStoryServiceImpl;
import lbd.fissst.springlbd.service.exception.UserStoryNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class UserStoryServiceImplTests {

    @Autowired
    private UserStoryServiceImpl userStoryService;

    @Autowired
    private SprintRepository sprintRepository;

    @Test
    void givenUserStoryWithNullName_whenSaved_shouldThrowException(){
        //given
        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                        .name(null)
                        .description("description")
                        .points(12)
                        .status(UserStoryStatus.TO_DO)
                        .build();

        //when & then
        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithEmptyName_whenSaved_shouldThrowException(){
        //given
        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                .name("")
                .description("description")
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        //when & then
        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithNullDescription_whenSaved_shouldThrowException(){
        //given
        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                .name("name")
                .description(null)
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        //when & then
        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithEmptyDescription_whenSaved_shouldThrowException(){
        //given
        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                .name("name")
                .description("")
                .points(12)
                .status(UserStoryStatus.TO_DO)
                .build();

        //when & then
        assertThrows(
                UserStoryNotValidException.class,
                () -> userStoryService.save(givenUserStory)
        );
    }

    @Test
    void givenUserStoryWithoutStatus_whenSaved_shouldSaveAndSetToDoStatus(){
        //given
        Sprint addedSprint = sprintRepository.save(
                Sprint.builder()
                        .name("name1")
                        .dateStart(LocalDate.now())
                        .dateEnd(LocalDate.now().plusDays(1))
                        .description("description1")
                        .status(SprintStatus.PENDING)
                        .build()
        );

        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                .name("name")
                .description("description")
                .points(12)
                .status(null)
                .build();

        //when
        UserStoryDTO savedUserStory = userStoryService.saveUserStoryAndAddToSprint(
                givenUserStory,
                addedSprint.getId()
        );

        //then
        assertEquals(UserStoryStatus.TO_DO, savedUserStory.getStatus());
    }

    @Test
    void givenUserStoryWithStatus_whenSaved_shouldSaveAndShouldNotChangeStatus(){
        //given
        Sprint addedSprint = sprintRepository.save(
                Sprint.builder()
                        .name("name1")
                        .dateStart(LocalDate.now())
                        .dateEnd(LocalDate.now().plusDays(1))
                        .description("description1")
                        .status(SprintStatus.PENDING)
                        .build()
        );

        UserStoryDTO givenUserStory = UserStoryDTO.builder()
                .name("name")
                .description("description")
                .points(12)
                .status(UserStoryStatus.DONE)
                .build();

        //when
        UserStoryDTO savedUserStory = userStoryService.saveUserStoryAndAddToSprint(
                givenUserStory,
                addedSprint.getId()
        );

        //then
        assertEquals(UserStoryStatus.DONE, savedUserStory.getStatus());
    }

    @Test
    void givenSprintId_whenFindUserStoriesBySprintId_shouldReturnAllUserStories(){
        //given
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint sprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .userStories(new HashSet<>())
                .build();

        for(int i = 0; i < 3; i++){
            sprint.getUserStories().add(
                    UserStory.builder()
                            .name("name")
                            .description("description")
                            .points(12)
                            .status(UserStoryStatus.DONE)
                            .build()
            );
        }
        sprintRepository.save(sprint);

        //when
        int amountOfUserStories = userStoryService.getUserStoriesBySprintId(sprint.getId()).size();

        //then
        assertEquals(3, amountOfUserStories);

    }
}
