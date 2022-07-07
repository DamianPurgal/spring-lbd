package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.Entity.Sprint;

import java.time.LocalDate;
import java.util.List;

public interface SprintService {
    Sprint save(Sprint sprint);

    List<Sprint> getAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo);

    Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id);
}