package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.DTO.Mappers.UserStoryMapper;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryGetDTO;
import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.event.UserStoryCreatedEvent;
import lbd.fissst.springlbd.exception.AppEntityNotFoundException;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import lbd.fissst.springlbd.exception.UserStoryNotValidException;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

    private UserStoryRepository userStoryRepository;

    private SprintRepository sprintRepository;

    private ApplicationEventPublisher publisher;

    private final UserStoryMapper mapper = Mappers.getMapper(UserStoryMapper.class);

    @Override
    @Transactional
    public UserStoryDTO save(UserStoryDTO userStoryDTO) {
        UserStory userStory = mapper.mapUserStoryDtoToUserStory(userStoryDTO);

        if(isUserStoryValid(userStory)){
            if(userStory.getStatus() == null){
                userStory.setStatus(UserStoryStatus.TO_DO);
            }
            UserStory savedUserStory = userStoryRepository.save(userStory);
            publisher.publishEvent(new UserStoryCreatedEvent(savedUserStory.getId()));
            return mapper.mapUserStoryToUserStoryDto(savedUserStory);

        }else{
            throw new UserStoryNotValidException("User story validation failed");
        }
    }

    @Override
    public List<UserStoryGetDTO> getUserStoriesBySprintId(Long id) {
        return userStoryRepository.findAllBySprintsId(id)
                .stream()
                .map(mapper::mapUserStoryToUserStoryGetDto)
                .toList();
    }

    @Override
    public Page<UserStoryDTO> getUserStoriesBySprintIdPageable(Long id, Pageable page) {
        return userStoryRepository.findAllBySprintsId(id, page)
                .map(mapper::mapUserStoryToUserStoryDto);
    }

    @Override
    public UserStoryDTO saveUserStoryAndAddToSprint(UserStoryDTO userStoryDTO, Long sprintId) {
        UserStory userStory = mapper.mapUserStoryDtoToUserStory(userStoryDTO);

        if(isUserStoryValid(userStory)){
            if(userStory.getStatus() == null){
                userStory.setStatus(UserStoryStatus.TO_DO);
            }
            Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(
                    () -> new AppEntityNotFoundException("Sprint not found")
            );
            userStory.setSprints(Set.of(sprint));

            return mapper.mapUserStoryToUserStoryDto(
                    userStoryRepository.save(userStory)
            );
        }else{
            throw new UserStoryNotValidException("User story validation failed");
        }

    }

    @Override
    public UserStoryDTO getUserStoryById(Long id) {
        return mapper.mapUserStoryToUserStoryDto(
                userStoryRepository.findById(id).orElseThrow(
                        () -> new AppEntityNotFoundException("UserStory not found")
                )
        );
    }

    @Override
    public void deleteUserStory(Long id) {
        try{
            userStoryRepository.deleteById(id);
        }catch(EmptyResultDataAccessException exception){
            throw new AppEntityNotFoundException("UserStory not found");
        }

    }

    @Override
    public Page<UserStoryDTO> getUserStoriesSortedAndPaged(Pageable page) {
        return userStoryRepository.findAll(page)
                .map(mapper::mapUserStoryToUserStoryDto);
    }

    //Utility
    private boolean isUserStoryValid(UserStory userStory){
        if(userStory.getName() == null){
            return false;
        }
        if(userStory.getName().isBlank()){
            return false;
        }
        if(userStory.getDescription() == null){
            return false;
        }
        if(userStory.getDescription().isBlank()){
            return false;
        }

        return true;
    }

}
