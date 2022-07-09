package lbd.fissst.springlbd.DTO.UserStory;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lombok.Data;

@Data
public class UserStoryDTO {

    private String name;
    private String description;
    private Integer points;
    private UserStoryStatus status;

}
