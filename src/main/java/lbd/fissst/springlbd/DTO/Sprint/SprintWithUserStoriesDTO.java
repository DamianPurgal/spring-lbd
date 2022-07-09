package lbd.fissst.springlbd.DTO.Sprint;

import lombok.Data;

import java.util.Set;

@Data
public class SprintWithUserStoriesDTO extends SprintDTO {

    private Set<UserStoryDTO> userStories;

    @Data
    public static class UserStoryDTO {

        private String name;

        private Integer points;
    }

}
