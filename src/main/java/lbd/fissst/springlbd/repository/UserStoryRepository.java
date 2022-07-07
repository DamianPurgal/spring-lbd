package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.UserStory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends PagingAndSortingRepository<UserStory, Long> {


    List<UserStory>  findAllBySprintsId(Long id);
}
