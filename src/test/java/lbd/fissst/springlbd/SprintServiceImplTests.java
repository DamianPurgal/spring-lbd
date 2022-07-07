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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SprintServiceImplTests {

    @Autowired
    SprintServiceImpl sprintService;


    @Test
    void givenSprintWithEqualDate_whenSaved_shouldThrowException() {
        Date date = new Date();
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
        Date dateEnd = new Date();
        Date dateStart = new Date(dateEnd.getTime() + new Date(1000 * 60 * 24 * 2).getTime());
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
        Date dateStart = new Date();
        Date dateEnd = new Date(dateStart.getTime() + new Date(1000 * 60).getTime());
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
        Date dateStart = new Date();
        Date dateEnd = new Date(dateStart.getTime() + new Date(1000 * 60).getTime());
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
        Date dateStart = new Date();
        Date dateEnd = new Date(dateStart.getTime() + new Date(1000 * 60).getTime());
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
        Date dateStart = new Date();
        Date dateEnd = new Date(dateStart.getTime() + new Date(1000 * 60).getTime());
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


}
