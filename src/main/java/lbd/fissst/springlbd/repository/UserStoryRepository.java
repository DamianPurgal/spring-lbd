package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory, Long> {


    List<UserStory>  findAllBySprintsId(Long id);
}
