package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SprintService {
    SprintDTO save(SprintDTO sprintDTO);

    List<SprintWithoutDescriptionDTO> getAllByGivenTimePeriodWithoutDescription(LocalDate dateFrom, LocalDate dateTo);

    Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id);

    Page<SprintDTO> getAllSortedAndPaged(Pageable page);

    List<? extends SprintDTO> getAllSprints(Boolean tasks);

    Integer getSumOfStoryPointsInSprint(Long sprintId);

    SprintDTO updateSprint(SprintPUTDTO sprintDataToUpdate, Long sprintId);

    SprintDTO getSprintById(Long id);
}
