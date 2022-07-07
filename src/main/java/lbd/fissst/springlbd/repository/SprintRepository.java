package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.Sprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    @Query("select s from Sprint s where ?1 >= s.dateStart")
    List<Sprint> findAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo);
}
