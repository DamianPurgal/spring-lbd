package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
}
