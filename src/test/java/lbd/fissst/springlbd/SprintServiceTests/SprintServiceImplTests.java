package lbd.fissst.springlbd.SprintServiceTests;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.service.implementation.SprintServiceImpl;
import lbd.fissst.springlbd.service.implementation.UserStoryServiceImpl;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SprintServiceImplTests {

    @Autowired
    private SprintServiceImpl sprintService;

    @Autowired
    private UserStoryServiceImpl userStoryService;

    @Autowired
    private SprintRepository sprintRepository;

    @Test
    void givenSprintWithEqualDate_whenSaved_shouldThrowException() {
        //given
        LocalDate date = LocalDate.now();
        SprintDTO givenSprint = SprintDTO.builder()
                .name("name1")
                .dateStart(date)
                .dateEnd(date)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        //when & then
        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithGreater_whenSaved_shouldThrowException(){
        //given
        LocalDate dateEnd = LocalDate.now();
        LocalDate dateStart = dateEnd.plusDays(1);
        SprintDTO givenSprint = SprintDTO.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        //when & then
        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithWrongStatus_whenSaved_shouldThrowException(){
        //given
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        SprintDTO givenSprint = SprintDTO.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        //when & then
        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintEmptyName_whenSaved_shouldThrowException(){
        //given
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        SprintDTO givenSprint = SprintDTO.builder()
                .name("")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        //when & then
        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithNullName_whenSaved_shouldThrowException(){
        //given
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        SprintDTO givenSprint = SprintDTO.builder()
                .name(null)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        //when & then
        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprint_whenSaved_shouldBeOK(){
        //given
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = dateStart.plusDays(1);
        SprintDTO givenSprint = SprintDTO.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        //when
        SprintDTO savedSprint = sprintService.save(givenSprint);

        //then
        assertNotNull(savedSprint);
    }

//    @Test
//    void givenTimePeriod_whenFindAllSprintsInPeriod_shouldReturnAllSprintsInTimePeriod(){
//        //given
//        List<Sprint> addedSprints = new ArrayList<>();
//        addedSprints.add(
//          sprintRepository.save(
//                  Sprint.builder()
//                          .name("name1")
//                          .dateStart(LocalDate.of(2022, 7, 1))
//                          .dateEnd(LocalDate.of(2022, 7, 10))
//                          .description("description1")
//                          .status(SprintStatus.PENDING)
//                          .build()
//          )
//        );
//
//        addedSprints.add(
//                sprintRepository.save(
//                        Sprint.builder()
//                                .name("name1")
//                                .dateStart(LocalDate.of(2022, 7, 3))
//                                .dateEnd(LocalDate.of(2022, 7, 4))
//                                .description("description1")
//                                .status(SprintStatus.PENDING)
//                                .build()
//                )
//        );
//
//        addedSprints.add(
//                sprintRepository.save(
//                        Sprint.builder()
//                                .name("name1")
//                                .dateStart(LocalDate.of(2022, 7, 6))
//                                .dateEnd(LocalDate.of(2022, 7, 7))
//                                .description("description1")
//                                .status(SprintStatus.PENDING)
//                                .build()
//                )
//        );
//
//        //when
//
//        List<SprintWithoutDescriptionDTO> sprintsFoundInTimePeriod = sprintService.getAllByGivenTimePeriodWithoutDescription(LocalDate.of(2022, 7, 2), LocalDate.of(2022, 7, 8));
//
//    }

    @Test
    void givenSprint_whenGetSumOfDoneUserStoriesOfSprint_shouldReturnCorrectResult(){
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
        Integer sum = sprintService.getSumOfStoryPointsInSprintWithDoneUserStories(sprint.getId());

        //then
        assertEquals(11, sum);
    }

    @Test
    void givenSprintWithUserStories_whenSaveWithUserStories_shouldSaveBoth(){
        //given
        SprintDTO sprintDTO = SprintDTO.builder()
                .name("name1")
                .dateStart(LocalDate.of(2023, 7, 1))
                .dateEnd(LocalDate.of(2023, 7, 4))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Set<UserStoryDTO> userStoriesDTO = new HashSet<>();

        userStoriesDTO.add(
                UserStoryDTO.builder()
                .name("name")
                .description("description")
                .points(5)
                .status(UserStoryStatus.DONE)
                .build()
        );

        userStoriesDTO.add(
                UserStoryDTO.builder()
                        .name("name")
                        .description("description")
                        .points(6)
                        .status(UserStoryStatus.DONE)
                        .build()
        );

        //when
        SprintWithUserStoriesDTO savedSprint = sprintService.saveSprintAndHisUserStories(sprintDTO, userStoriesDTO);

        //then
        assertNotNull(savedSprint);
        assertThat(savedSprint.getUserStories(), hasSize(2));
    }

}
