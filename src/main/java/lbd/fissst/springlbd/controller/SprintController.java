package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lbd.fissst.springlbd.service.definition.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sprints")
@AllArgsConstructor
public class SprintController {

    private SprintService sprintService;

    @GetMapping
    public List<? extends SprintDTO> getAllSprints(@RequestParam("tasks") Boolean tasks){
        return sprintService.getAllSprints(tasks);
    }

    @GetMapping("storyPoints/{sprintId}")
    public Integer getSumOfStoryPointsInSprint(@PathVariable("sprintId") Long sprintId){
        return sprintService.getSumOfStoryPointsInSprint(sprintId);
    }

    @PutMapping("/status/{sprintId}")
    public void updateSprintStatus(@RequestParam SprintStatus sprintStatus, @PathVariable("sprintId") Long sprintId){
        SprintPUTDTO sprintDTO = SprintPUTDTO
                .builder()
                .status(sprintStatus)
                .build();

        sprintService.updateSprint(sprintDTO, sprintId);
    }

    @GetMapping("/inTimePeriod")
    public List<SprintWithoutDescriptionDTO> getAllSprintsInTimePeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd){
        return sprintService.getAllByGivenTimePeriodWithoutDescription(dateFrom, dateEnd);
    }
}
