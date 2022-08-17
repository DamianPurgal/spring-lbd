package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryGetDTO;
import lbd.fissst.springlbd.Entity.UserStory;
import org.mapstruct.Mapper;

@Mapper
public interface UserStoryMapper {

    UserStory mapUserStoryToUserStory(UserStoryDTO userStoryDTO);

    UserStoryGetDTO mapUserStoryToUserStoryGetDto(UserStory userStory);

    UserStoryDTO mapUserStoryToUserStoryDto(UserStory userStory);

}
