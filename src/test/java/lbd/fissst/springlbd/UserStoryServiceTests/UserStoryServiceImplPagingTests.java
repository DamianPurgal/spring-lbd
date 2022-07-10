package lbd.fissst.springlbd.UserStoryServiceTests;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.service.dataGenerator.UserStoryGenerator;
import lbd.fissst.springlbd.service.implementation.SprintServiceImpl;
import lbd.fissst.springlbd.service.implementation.UserStoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class UserStoryServiceImplPagingTests {

    @Autowired
    private UserStoryServiceImpl userStoryService;

    @Autowired
    private SprintServiceImpl sprintService;

    @Autowired
    private UserStoryGenerator userStoryGenerator;

    @Test
    void givenDataCreated_whenFindAllUserStoriesBySprintIdPaginated_thenSuccess(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint sprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        sprintService.save(sprint);

        for(int i = 0; i < 3; i++){
            UserStory userStory = UserStory.builder()
                    .name("name")
                    .description("description")
                    .points(12)
                    .status(UserStoryStatus.DONE)
                    .sprints(Set.of(sprint))
                    .build();
            userStoryService.save(userStory);
        }

        Page<UserStory> retrievedUserStories = userStoryService.getUserStoriesBySprintIdPageable(sprint.getId(), PageRequest.of(0,2));

        assertThat(retrievedUserStories.getContent(), hasSize(2));
    }


}
