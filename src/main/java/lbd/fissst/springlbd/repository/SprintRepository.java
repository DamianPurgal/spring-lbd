package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
}
