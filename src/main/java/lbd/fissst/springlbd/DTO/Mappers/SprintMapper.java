package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryDTO;
import lbd.fissst.springlbd.DTO.UserStory.UserStoryNameAndPointsDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import lbd.fissst.springlbd.Entity.UserStory;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SprintMapper {

    SprintDTO mapSprintToSprintDto(Sprint sprint);

    SprintWithUserStoriesDTO mapSprintToSprintWithUserStoriesDto(Sprint sprint);

    Sprint mapSprintPutDtoToSprint(SprintPUTDTO sprintPUTDTO, @MappingTarget Sprint sprint);

    SprintWithoutDescriptionDTO mapSprintToSprintWithoutDescriptionDto(Sprint sprint);

    Sprint mapSprintDtoToSprint(SprintDTO sprintDTO);

}
