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
import lbd.fissst.springlbd.exception.AppEntityNotFoundException;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.SprintService;
import lbd.fissst.springlbd.exception.SprintNotValidException;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        if(isSprintValid(sprint)){
            return mapper.mapSprintToSprintDto(
                    sprintRepository.save(sprint)
            );
        }else{
            throw new SprintNotValidException("Sprint validation failed");
        }
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
                () -> new AppEntityNotFoundException("Sprint not found")
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
                () -> new AppEntityNotFoundException("Sprint not found")
        );

        if(isSprintValid(sprint)){
            return mapper.mapSprintToSprintDto(
                    sprintRepository.save(sprint)
            );
        }else{
            throw new SprintNotValidException("Sprint validation failed");
        }
    }

    @Override
    public SprintDTO getSprintById(Long id) {
        return mapper.mapSprintToSprintDto(sprintRepository.findById(id)
                .orElseThrow(
                        () -> new AppEntityNotFoundException("Sprint not found")
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
                        () -> new AppEntityNotFoundException("No sprint found to add to")
                );
        UserStory userStory = userStoryRepository.findById(userStoryCreatedEvent.getUserStoryId())
                .orElseThrow(
                        () -> new AppEntityNotFoundException("userStory not found")
                );

        if(sprint.getUserStories() != null){
            sprint.getUserStories().add(userStory);
        }else{
            sprint.setUserStories(Set.of(userStory));
        }
        sprintRepository.save(sprint);
    }

    //Utility
    private boolean isSprintValid(Sprint sprint){
        if(sprint.getName() == null){
            return false;
        }
        if(sprint.getName().isBlank()){
            return false;
        }
        if(sprint.getStatus() == null){
            return false;
        }
        if(sprint.getDateStart().compareTo(sprint.getDateEnd()) >= 0){
            return false;
        }

        return true;
    }
}
