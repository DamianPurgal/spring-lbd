package lbd.fissst.springlbd.events.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStoryCreatedEvent {
    private Long userStoryId;
}
