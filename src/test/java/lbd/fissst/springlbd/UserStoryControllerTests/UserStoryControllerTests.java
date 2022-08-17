package lbd.fissst.springlbd.UserStoryControllerTests;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.controller.UserStoryController;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.SprintService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class UserStoryControllerTests {

    @Autowired
    private UserStoryController userStoryController;

    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Test
    public void givenUserStory_whenSaveUserStory_thenSuccess(){
        //given
        Sprint sprint = Sprint.builder()
                .name("sprintNameTest")
                .description("sprintDescriptionTest")
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(1))
                .status(SprintStatus.PENDING)
                .build();

        sprint = sprintRepository.save(sprint);

        UserStoryDTO userStoryDTO = UserStoryDTO.builder()
                .name("nameTest")
                .description("descriptionTest")
                .points(12)
                .status(UserStoryStatus.DONE)
                .build();

        //when
        userStoryController.addUserStoryToSprint(userStoryDTO, sprint.getId());

        //then
        List<UserStory> userstories = userStoryRepository.findAllBySprintsId(sprint.getId());

        assertThat(userstories, hasSize(1));
    }
}
