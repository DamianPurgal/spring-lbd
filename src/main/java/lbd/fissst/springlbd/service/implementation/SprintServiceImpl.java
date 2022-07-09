package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.SprintService;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;

    private UserStoryRepository userStoryRepository;

    @Override
    @Transactional
    public Sprint save(Sprint sprint) {

        if(sprint.getName() == null){
            throw new SprintNotValidException("name cannot be null!");
        }
        if(sprint.getName().isBlank()){
            throw new SprintNotValidException("name cannot be empty!");
        }
        if(sprint.getStatus() == null){
            throw new SprintNotValidException("invalid status!");
        }
        if(sprint.getDateStart().compareTo(sprint.getDateEnd()) >= 0){
            throw new SprintNotValidException("Start date cannot be greater or equal to end date!");
        }

        return sprintRepository.save(sprint);
    }

    @Override
    public List<Sprint> getAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo) {
        return sprintRepository.findAllByGivenTimePeriod(dateFrom, dateTo);
    }

    @Override
    public Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id) {
        return sprintRepository.getSumOfStoryPointsInSprintWithDoneUserStories(id);
    }

    @Override
    public Page<Sprint> getAllSortedAndPaged(Pageable page) {
        return sprintRepository.findAll(page);
    }

    @Override
    @Transactional
    public Sprint saveSprintAndHisUserStories(Sprint sprint) {
        userStoryRepository.saveAll(sprint.getUserStories());
        return sprintRepository.save(sprint);
    }

    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    public Integer getSumOfStoryPointsInSprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprint not found")
        );

        return sprint.getUserStories()
                    .stream()
                    .map(UserStory::getPoints)
                    .mapToInt(Integer::intValue)
                    .reduce(0, Integer::sum);
    }
}
