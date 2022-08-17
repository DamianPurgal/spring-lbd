package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryGetDTO;
import lbd.fissst.springlbd.service.definition.UserStoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/userstories")
@AllArgsConstructor
public class UserStoryController {

    private UserStoryService userStoryService;

    @PostMapping("/{sprintId}")
    public void addUserStoryToSprint(@RequestBody UserStoryDTO userStoryDTO, @PathVariable("sprintId") Long sprintId){
        if(sprintId == null || userStoryDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        userStoryService.saveUserStoryAndAddToSprint(userStoryDTO, sprintId);
    }

    @GetMapping("/{sprintId}")
    public List<UserStoryGetDTO> getUserStoriesWithSprintId(@PathVariable("sprintId") Long sprintId){
        return userStoryService.getUserStoriesBySprintId(sprintId);
    }

    @GetMapping("/description/{userStoryId}")
    public String getUserStoryDescription(@PathVariable("userStoryId") Long userStoryId){
        return userStoryService
                .getUserStoryById(userStoryId)
                .getDescription();
    }

    @DeleteMapping("/{userStoryId}")
    public void deleteUserStoryById(@PathVariable("userStoryId") Long userStoryId){
        userStoryService.deleteUserStory(userStoryId);
    }

    @GetMapping("/sortedByName")
    public List<UserStoryDTO> getUserStoriesSortedByNameAndPageable(@RequestParam Integer page, @RequestParam Integer amount){
        if(amount < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "amount must not be less than one");
        }if(page < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page must not be less than one");
        }
        return userStoryService.getUserStoriesSortedAndPaged(
                PageRequest.of(page, amount, Sort.by(Sort.Order.asc("name")))
                )
                .getContent();
    }

    @PostMapping
    public void addUserStory(@RequestBody UserStoryDTO userStoryDTO){
        userStoryService.save(userStoryDTO);
    }
}
