package lbd.fissst.springlbd.DTO.UserStory;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lombok.Data;

@Data
public class UserStoryGetDTO {

    private String name;
    private Integer points;
    private UserStoryStatus status;

}
