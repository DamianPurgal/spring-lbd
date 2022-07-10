package lbd.fissst.springlbd.SprintControllerTests;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.controller.SprintController;
import lbd.fissst.springlbd.service.definition.SprintService;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SprintControllerTests {

    @Autowired
    SprintController sprintController;

    @Autowired
    SprintService sprintService;

    @Autowired
    UserStoryService userStoryService;

    @Test
    public void givenSprintStatusAndId_whenUpdateSprintStatus_thenSuccess(){
        Sprint sprint = Sprint.builder()
                .name("sprintNameTest")
                .description("sprintDescriptionTest")
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(1))
                .status(SprintStatus.PENDING)
                .build();

        sprint = sprintService.save(sprint);

        sprintController.updateSprintStatus(SprintStatus.FINISHED, sprint.getId());

        Sprint updatedSprint = sprintService.getSprintById(sprint.getId());

        assertEquals(SprintStatus.FINISHED, updatedSprint.getStatus());
    }

    @Test
    @Transactional
    public void givenSprintId_whenSumAllStoryPointsOfSprint_shouldReturnCorrectSum(){
        Sprint sprint = Sprint.builder()
                .name("name1")
                .dateStart(LocalDate.of(2023, 7, 1))
                .dateEnd(LocalDate.of(2023, 7, 4))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        UserStory userStoryA = UserStory.builder()
                .name("name")
                .description("description")
                .points(5)
                .status(UserStoryStatus.DONE)
                .build();
        userStoryA = userStoryService.save(userStoryA);

        UserStory userStoryB = UserStory.builder()
                .name("name")
                .description("description")
                .points(6)
                .status(UserStoryStatus.DONE)
                .build();
        userStoryB = userStoryService.save(userStoryB);

        UserStory userStoryC = UserStory.builder()
                .name("name")
                .description("description")
                .points(12)
                .status(UserStoryStatus.IN_PROGRESS)
                .build();
        userStoryC = userStoryService.save(userStoryC);

        sprint.setUserStories(Set.of(userStoryA, userStoryB, userStoryC));

        sprint = sprintService.save(sprint);

        Integer sum = sprintController.getSumOfStoryPointsInSprint(sprint.getId());

        assertEquals(23, sum);
    }
}
