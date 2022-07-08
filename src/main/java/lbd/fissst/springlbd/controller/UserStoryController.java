package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Mappers.UserStoryMapper;
import lbd.fissst.springlbd.DTO.UserStoryDTO;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/userStory")
@RequiredArgsConstructor
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    private final UserStoryMapper mapper = Mappers.getMapper(UserStoryMapper.class);

    @PostMapping("/{sprintId}")
    public void addUserStoryToSprint(@RequestBody UserStoryDTO userStoryDTO, @PathVariable("sprintId") Long sprintId){
        if(sprintId == null || userStoryDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        UserStory userStory = mapper.mapToUserStory(userStoryDTO);

        userStoryService.saveUserStoryAndAddToSprint(userStory, sprintId);
    }
}
