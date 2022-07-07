package lbd.fissst.springlbd.service;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.exception.UserStoryNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService{

    @Autowired
    UserStoryRepository userStoryRepository;

    @Override
    @Transactional
    public UserStory save(UserStory userStory) {

        if(userStory.getStatus() == null){
            userStory.setStatus(UserStoryStatus.TO_DO);
        }
        if(userStory.getName().isBlank()){
            throw new UserStoryNotValidException("Name cannot be empty!");
        }
        if(userStory.getDescription().isBlank()){
            throw new UserStoryNotValidException("Description cannot be empty!");
        }
        return userStoryRepository.save(userStory);
    }
}
