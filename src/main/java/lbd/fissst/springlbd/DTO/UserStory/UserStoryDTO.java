package lbd.fissst.springlbd.DTO.UserStory;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoryDTO {

    private String name;
    private String description;
    private Integer points;
    private UserStoryStatus status;

}
