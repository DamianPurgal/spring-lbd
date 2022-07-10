package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SprintService {
    Sprint save(Sprint sprint);

    List<Sprint> getAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo);

    Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id);

    Page<Sprint> getAllSortedAndPaged(Pageable page);

    Sprint saveSprintAndHisUserStories(Sprint sprint);

    List<Sprint> getAllSprints();

    Integer getSumOfStoryPointsInSprint(Long sprintId);

    Sprint updateSprint(SprintPUTDTO sprintDataToUpdate, Long sprintId);
}
