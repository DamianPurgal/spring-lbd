package lbd.fissst.springlbd.events.eventListener;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.events.event.UserStoryCreatedEvent;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserStoryCreatedEventListener {

    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    SprintRepository sprintRepository;

    @EventListener
    public void handleUserStoryCreatedEvent(UserStoryCreatedEvent userStoryCreatedEvent){
        Sprint sprint = sprintRepository.findTopByStatusOrderByDateStartDesc(SprintStatus.PENDING)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sprint not found")
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
