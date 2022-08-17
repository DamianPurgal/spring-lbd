package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.event.UserStoryCreatedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface SprintService {
    SprintDTO save(SprintDTO sprintDTO);

    List<SprintWithoutDescriptionDTO> getAllByGivenTimePeriodWithoutDescription(LocalDate dateFrom, LocalDate dateTo);

    Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id);

    Page<SprintDTO> getAllSortedAndPaged(Pageable page);

    List<? extends SprintDTO> getAllSprints(Boolean tasks);

    Integer getSumOfStoryPointsInSprint(Long sprintId);

    SprintDTO updateSprint(SprintPUTDTO sprintDataToUpdate, Long sprintId);

    SprintDTO getSprintById(Long id);

    SprintWithUserStoriesDTO saveSprintAndHisUserStories(SprintDTO sprintDTO, Set<UserStoryDTO> userStoriesDTO);

    void userStoryCreatedEventHandler(UserStoryCreatedEvent userStoryCreatedEvent);
}
