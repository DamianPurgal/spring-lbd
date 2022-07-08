package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import lbd.fissst.springlbd.service.exception.UserStoryNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired
    UserStoryRepository userStoryRepository;

    @Override
    @Transactional
    public UserStory save(UserStory userStory) {

        if(userStory.getStatus() == null){
            userStory.setStatus(UserStoryStatus.TO_DO);
        }
        if(userStory.getName() == null){
            throw new UserStoryNotValidException("Name cannot be null!");
        }
        if(userStory.getName().isBlank()){
            throw new UserStoryNotValidException("Name cannot be empty!");
        }
        if(userStory.getDescription() == null){
            throw new UserStoryNotValidException("Description cannot be null!");
        }
        if(userStory.getDescription().isBlank()){
            throw new UserStoryNotValidException("Description cannot be empty!");
        }
        return userStoryRepository.save(userStory);
    }

    @Override
    public List<UserStory> getUserStoriesBySprintId(Long id) {
        return userStoryRepository.findAllBySprintsId(id);
    }

    @Override
    public Page<UserStory> getUserStoriesBySprintIdPageable(Long id, Pageable page) {
        return userStoryRepository.findAllBySprintsId(id, page);
    }

}
