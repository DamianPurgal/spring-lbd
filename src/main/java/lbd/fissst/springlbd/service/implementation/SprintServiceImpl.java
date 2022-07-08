package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.service.definition.SprintService;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    @Autowired
    SprintRepository sprintRepository;

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

}
