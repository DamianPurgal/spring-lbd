package lbd.fissst.springlbd.DTO.Sprint;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryNameAndPointsDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
public class SprintWithUserStoriesDTO extends SprintDTO {

    private Set<UserStoryNameAndPointsDTO> userStories;

}
