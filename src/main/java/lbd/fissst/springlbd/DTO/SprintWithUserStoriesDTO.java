package lbd.fissst.springlbd.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
public class SprintWithUserStoriesDTO extends SprintDTO {

    private Set<UserStoryDTO> userStories;
}
