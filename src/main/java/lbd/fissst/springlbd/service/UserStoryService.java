package lbd.fissst.springlbd.service;

import lbd.fissst.springlbd.Entity.UserStory;

import java.util.List;

public interface UserStoryService {

    List<UserStory> getUserStoriesBySprintId(Long id);

    UserStory save(UserStory userStory);
}
