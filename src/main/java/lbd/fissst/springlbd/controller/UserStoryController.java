package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Mappers.UserStoryMapper;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryGetDTO;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/userstories")
@AllArgsConstructor
public class UserStoryController {

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

    @GetMapping("/{sprintId}")
    public List<UserStoryGetDTO> getUserStoriesWithSprintId(@PathVariable("sprintId") Long sprintId){
        return userStoryService.getUserStoriesBySprintId(sprintId)
                .stream()
                .map(mapper::mapToUserStoryGetDTO)
                .toList();
    }

    @GetMapping("/description/{userStoryId}")
    public String getUserStoryDescription(@PathVariable("userStoryId") Long userStoryId){
        return userStoryService
                .getUserStoryById(userStoryId)
                .getDescription();
    }


}
