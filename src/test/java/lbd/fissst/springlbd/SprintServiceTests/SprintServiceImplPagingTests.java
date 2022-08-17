package lbd.fissst.springlbd.SprintServiceTests;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.repository.SprintRepository;
import lbd.fissst.springlbd.service.implementation.SprintServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SprintServiceImplPagingTests {

    @Autowired
    private SprintServiceImpl sprintService;

    @Autowired
    private SprintRepository sprintRepository;
    @Test
    void givenDataCreated_whenFindAllPaginatedAndSorted_thenSuccess(){
        //given
        LocalDate dateStart = LocalDate.of(2050, 5, 10);

        for(int i = 0;i < 5 ;i++){
            Sprint sprint = Sprint.builder()
                    .name("name")
                    .dateStart(dateStart.minusDays(i))
                    .dateEnd(dateStart.plusDays(i + 1))
                    .description("description")
                    .status(SprintStatus.FINISHED)
                    .build();
            sprintRepository.save(sprint);
        }

        //when
        Page<SprintDTO> retrievedSprints = sprintService.getAllSortedAndPaged(
                PageRequest.of(
                        0,
                        5,
                        Sort.by(Sort.Order.desc("dateStart")))
        );

        //then
        assertThat(retrievedSprints.getContent(), hasSize(5));

        assertTrue(retrievedSprints.getContent().get(0).getDateStart().isEqual(dateStart));


    }

}
