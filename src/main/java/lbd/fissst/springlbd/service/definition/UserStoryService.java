package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryGetDTO;
import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserStoryService {

    List<UserStoryGetDTO> getUserStoriesBySprintId(Long id);

    Page<UserStoryDTO> getUserStoriesBySprintIdPageable(Long id, Pageable page);

    UserStoryDTO save(UserStoryDTO userStoryDTO);

    UserStoryDTO saveUserStoryAndAddToSprint(UserStoryDTO userStoryDTO, Long sprintId);

    UserStoryDTO getUserStoryById(Long id);

    void deleteUserStory(Long id);

    Page<UserStoryDTO> getUserStoriesSortedAndPaged(Pageable page);

}
