package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.DTO.Mappers.SprintMapper;
import lbd.fissst.springlbd.DTO.Mappers.UserStoryMapper;
import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.event.UserStoryCreatedEvent;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.SprintService;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;

    private UserStoryRepository userStoryRepository;

    private final SprintMapper mapper = Mappers.getMapper(SprintMapper.class);
    @Override
    @Transactional
    public SprintDTO save(SprintDTO sprintDTO) {

        Sprint sprint = mapper.mapSprintDtoToSprint(sprintDTO);

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

        return mapper.mapSprintToSprintDto(
                sprintRepository.save(sprint)
        );
    }

    @Override
    public List<SprintWithoutDescriptionDTO> getAllByGivenTimePeriodWithoutDescription(LocalDate dateFrom, LocalDate dateTo) {
        return sprintRepository.findAllByGivenTimePeriod(dateFrom, dateTo)
                .stream()
                .map(mapper::mapSprintToSprintWithoutDescriptionDto)
                .toList();
    }

    @Override
    public Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id) {
        return sprintRepository.getSumOfStoryPointsInSprintWithDoneUserStories(id);
    }

    @Override
    public Page<SprintDTO> getAllSortedAndPaged(Pageable page) {
        return sprintRepository.findAll(page)
                .map(mapper::mapSprintToSprintDto);
    }

    @Override
    public List<? extends SprintDTO> getAllSprints(Boolean tasks) {
        List<Sprint> sprints = sprintRepository.findAll();

        if(tasks){
            return sprints.stream().map(mapper::mapSprintToSprintWithUserStoriesDto).toList();
        }else {
            return sprints.stream().map(mapper::mapSprintToSprintDto).toList();
        }
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

    @Override
    public SprintDTO updateSprint(SprintPUTDTO sprintDataToUpdate, Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .map(s -> mapper.mapSprintPutDtoToSprint(sprintDataToUpdate, s))
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprint not found")
        );

        return mapper.mapSprintToSprintDto(
                sprintRepository.save(sprint)
        );
    }

    @Override
    public SprintDTO getSprintById(Long id) {
        return mapper.mapSprintToSprintDto(sprintRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprint not found")
                )
        );
    }

    @Override
    public SprintWithUserStoriesDTO saveSprintAndHisUserStories(SprintDTO sprintDTO, Set<UserStoryDTO> userStoriesDTO) {
        Sprint sprintToAdd = mapper.mapSprintDtoToSprint(sprintDTO);
        sprintToAdd.setUserStories(
                userStoriesDTO
                        .stream()
                        .map(
                                Mappers.getMapper(UserStoryMapper.class)::mapUserStoryDtoToUserStory
                        )
                        .collect(Collectors.toSet())
        );

        return mapper.mapSprintToSprintWithUserStoriesDto(
                sprintRepository.save(sprintToAdd)
        );
    }

    @EventListener
    @Override
    public void userStoryCreatedEventHandler(UserStoryCreatedEvent userStoryCreatedEvent){
        Sprint sprint = sprintRepository.findTopByStatusOrderByDateStartDesc(SprintStatus.PENDING)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No sprint found to add to")
                );
        UserStory userStory = userStoryRepository.findById(userStoryCreatedEvent.getUserStoryId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userStory not found")
                );

        if(sprint.getUserStories() != null){
            sprint.getUserStories().add(userStory);
        }else{
            sprint.setUserStories(Set.of(userStory));
        }
        sprintRepository.save(sprint);

    }
}
