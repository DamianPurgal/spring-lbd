package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.repository.CrudRepository;

public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
}
