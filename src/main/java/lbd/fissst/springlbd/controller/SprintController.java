package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Mappers.SprintMapper;
import lbd.fissst.springlbd.DTO.SprintDTO;
import lbd.fissst.springlbd.service.definition.SprintService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
@RequiredArgsConstructor
public class SprintController {

    @Autowired
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



}
