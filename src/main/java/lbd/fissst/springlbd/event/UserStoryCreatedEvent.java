package lbd.fissst.springlbd.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStoryCreatedEvent {
    private Long userStoryId;
}
