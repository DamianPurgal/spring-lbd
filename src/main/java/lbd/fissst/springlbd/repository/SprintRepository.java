package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends PagingAndSortingRepository<Sprint, Long> {

    @Query("select s from Sprint s where ?1 <= s.dateStart and ?2 >= s.dateEnd")
    List<Sprint> findAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo);

    @Query("select sum(u.points) from Sprint s join s.userStories u where s.id = :id and u.status = 'DONE'")
    Integer getSumOfStoryPointsInSprintWithDoneUserStories(Long id);

    Page<Sprint> findAll(Pageable page);

    List<Sprint> findAll();

    Optional<Sprint> findById(Long id);

    Optional<Sprint> findTopByStatusOrderByDateStartDesc(SprintStatus status);
}
