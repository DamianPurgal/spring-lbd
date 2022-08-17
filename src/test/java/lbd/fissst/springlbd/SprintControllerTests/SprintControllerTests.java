package lbd.fissst.springlbd.SprintControllerTests;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.controller.SprintController;
import lbd.fissst.springlbd.repository.SprintRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SprintControllerTests {

    @Autowired
    private SprintController sprintController;

    @Autowired
    private SprintRepository sprintRepository;


    @Test
    public void givenSprintStatusAndId_whenUpdateSprintStatus_thenSuccess(){
        //given
        Sprint sprint = Sprint.builder()
                .name("sprintNameTest")
                .description("sprintDescriptionTest")
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(1))
                .status(SprintStatus.PENDING)
                .build();

        sprint = sprintRepository.save(sprint);

        //when
        sprintController.updateSprintStatus(SprintStatus.FINISHED, sprint.getId());

        //then
        Sprint updatedSprint = sprintRepository.findById(sprint.getId()).get();

        assertEquals(SprintStatus.FINISHED, updatedSprint.getStatus());
    }

    @Test
    @Transactional
    public void givenSprintId_whenSumAllStoryPointsOfSprint_shouldReturnCorrectSum(){
        //given
        Sprint sprint = Sprint.builder()
                .name("name1")
                .dateStart(LocalDate.of(2023, 7, 1))
                .dateEnd(LocalDate.of(2023, 7, 4))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        sprint.setUserStories(new HashSet<>());

        sprint.getUserStories().add(
                UserStory.builder()
                        .name("name")
                        .description("description")
                        .points(5)
                        .status(UserStoryStatus.DONE)
                        .build()
        );

        sprint.getUserStories().add(
                UserStory.builder()
                        .name("name")
                        .description("description")
                        .points(6)
                        .status(UserStoryStatus.DONE)
                        .build()
        );

        sprint.getUserStories().add(
                UserStory.builder()
                        .name("name")
                        .description("description")
                        .points(12)
                        .status(UserStoryStatus.IN_PROGRESS)
                        .build()
        );

        sprint = sprintRepository.save(sprint);

        //when
        Integer sum = sprintController.getSumOfStoryPointsInSprint(sprint.getId());

        //then
        assertEquals(23, sum);
    }
}
