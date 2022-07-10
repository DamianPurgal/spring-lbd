package lbd.fissst.springlbd.SprintServiceTests;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.service.implementation.SprintServiceImpl;
import lbd.fissst.springlbd.service.implementation.UserStoryServiceImpl;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SprintServiceImplTests {

    @Autowired
    private SprintServiceImpl sprintService;

    @Autowired
    private UserStoryServiceImpl userStoryService;

    @Test
    void givenSprintWithEqualDate_whenSaved_shouldThrowException() {
        LocalDate date = LocalDate.now();
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(date)
                .dateEnd(date)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithGreater_whenSaved_shouldThrowException(){
        LocalDate dateEnd = LocalDate.now();
        LocalDate dateStart = dateEnd.plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithWrongStatus_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintEmptyName_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithNullName_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name(null)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprint_whenSaved_shouldBeOK(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint savedSprint = sprintService.save(givenSprint);

        assertNotNull(savedSprint);
    }

    @Test
    void givenTimePeriod_whenFindAllSprintsInPeriod_shouldReturnAllSprintsInTimePeriod(){


        Sprint sprintA = Sprint.builder()
                .name("name1")
                .dateStart(LocalDate.of(2022, 7, 1))
                .dateEnd(LocalDate.of(2022, 7, 10))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint sprintB = Sprint.builder()
                .name("name1")
                .dateStart(LocalDate.of(2022, 7, 3))
                .dateEnd(LocalDate.of(2022, 7, 4))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint sprintC = Sprint.builder()
                .name("name1")
                .dateStart(LocalDate.of(2022, 7, 6))
                .dateEnd(LocalDate.of(2022, 7, 7))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        sprintA = sprintService.save(sprintA);
        sprintB = sprintService.save(sprintB);
        sprintC = sprintService.save(sprintC);

        List<Sprint> sprintsFoundInTimePeriod = sprintService.getAllByGivenTimePeriod(LocalDate.of(2022, 7, 2), LocalDate.of(2022, 7, 8));
        List<Long> sprintsFoundIds = sprintsFoundInTimePeriod.stream().map(Sprint::getId).toList();

        assertTrue(sprintsFoundIds.contains(sprintB.getId()));
        assertTrue(sprintsFoundIds.contains(sprintC.getId()));
        assertFalse(sprintsFoundIds.contains(sprintA.getId()));
    }

    @Test
    void givenSprint_whenGetSumOfDoneUserStoriesOfSprint_shouldReturnCorrectResult(){
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

        Integer sum = sprintService.getSumOfStoryPointsInSprintWithDoneUserStories(sprint.getId());

        assertEquals(11, sum);
    }

    @Test
    void givenSprintWithUserStories_whenSaveWithUserStories_shouldSaveBoth(){
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

        UserStory userStoryB = UserStory.builder()
                .name("name")
                .description("description")
                .points(6)
                .status(UserStoryStatus.DONE)
                .build();

        sprint.setUserStories(Set.of(userStoryA, userStoryB));

        Sprint savedSprint = sprintService.saveSprintAndHisUserStories(sprint);

        assertNotNull(sprint);
        assertThat(savedSprint.getUserStories(), hasSize(2));
    }

}
