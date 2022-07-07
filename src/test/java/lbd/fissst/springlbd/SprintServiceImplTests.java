package lbd.fissst.springlbd;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.service.SprintServiceImpl;
import lbd.fissst.springlbd.service.exception.SprintNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SprintServiceImplTests {

    @Autowired
    SprintServiceImpl sprintService;


    @Test
    void givenSprintWithEqualDate_whenSaved_shouldThrowException() {
        LocalDate date = LocalDate.now();
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(date)
                .dateEnd(date)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithGreater_whenSaved_shouldThrowException(){
        LocalDate dateEnd = LocalDate.now();
        LocalDate dateStart = LocalDate.now().plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithWrongStatus_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintEmptyName_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprintWithNullName_whenSaved_shouldThrowException(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name(null)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(null)
                .build();

        assertThrows(
                SprintNotValidException.class,
                () -> sprintService.save(givenSprint)
        );
    }

    @Test
    void givenSprint_whenSaved_shouldBeOK(){
        LocalDate dateStart = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(1);
        Sprint givenSprint = Sprint.builder()
                .name("name1")
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint savedSprint = sprintService.save(givenSprint);

        assertNotNull(savedSprint);
    }

    @Test
    void givenTimePeriod_WhenFindAllSprintsInPeriod_shouldReturnAllSprintsInTimePeriod(){

        LocalDate date = LocalDate.of(2023, 7, 7);

        Sprint sprintA = Sprint.builder()
                .name("name1")
                .dateStart(date)
                .dateEnd(date.plusDays(9))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint sprintB = Sprint.builder()
                .name("name1")
                .dateStart(date.plusDays(1))
                .dateEnd(date.plusDays(3))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        Sprint sprintC = Sprint.builder()
                .name("name1")
                .dateStart(date.plusDays(7))
                .dateEnd(date.plusDays(8))
                .description("description1")
                .status(SprintStatus.PENDING)
                .build();

        sprintA = sprintService.save(sprintA);
        sprintB = sprintService.save(sprintB);
        sprintC = sprintService.save(sprintC);

        List<Sprint> sprintsFoundInTimePeriod = sprintService.getAllByGivenTimePeriod(date.plusDays(1), date.plusDays(3));
        List<Long> sprintsFoundIds = sprintsFoundInTimePeriod.stream().map(Sprint::getId).toList();

        assertTrue(sprintsFoundIds.contains(sprintA.getId()));
        assertTrue(sprintsFoundIds.contains(sprintB.getId()));
        assertFalse(sprintsFoundIds.contains(sprintC.getId()));
    }

}
