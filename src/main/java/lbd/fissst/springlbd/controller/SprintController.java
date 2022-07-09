package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Mappers.SprintMapper;
import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.service.definition.SprintService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
@AllArgsConstructor
public class SprintController {

    private SprintService sprintService;

    private final SprintMapper mapper = Mappers.getMapper(SprintMapper.class);

    @GetMapping
    public List<? extends SprintDTO> getAllSprints(@RequestParam("tasks") Boolean tasks){
        if(tasks){
            return sprintService.getAllSprints().stream().map(mapper::mapToSprintWithUserStoriesDTO).toList();
        }else {
            return sprintService.getAllSprints().stream().map(mapper::mapToSprintDTO).toList();
        }

    }

    @GetMapping("storyPoints/{sprintId}")
    public Integer getSumOfStoryPointsInSprint(@PathVariable("sprintId") Long sprintId){
        return sprintService.getSumOfStoryPointsInSprint(sprintId);
    }

}
