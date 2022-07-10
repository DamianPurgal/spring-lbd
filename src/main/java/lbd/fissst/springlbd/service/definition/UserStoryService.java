package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserStoryService {

    List<UserStory> getUserStoriesBySprintId(Long id);

    Page<UserStory> getUserStoriesBySprintIdPageable(Long id, Pageable page);

    UserStory save(UserStory userStory);

    UserStory saveUserStoryAndAddToSprint(UserStory userStory, Long sprintId);

    UserStory getUserStoryById(Long id);

    void deleteUserStory(Long id);

    Page<UserStory> getUserStoriesSortedAndPaged(Pageable page);
}
