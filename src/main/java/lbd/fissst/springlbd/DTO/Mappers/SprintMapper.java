package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintPUTDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithoutDescriptionDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SprintMapper {

    SprintDTO mapSprintToSprintDto(Sprint sprint);

    SprintWithUserStoriesDTO mapSprintToSprintWithUserStoriesDto(Sprint sprint);

    Sprint mapSprintPutDtoToSprint(SprintPUTDTO sprintPUTDTO, @MappingTarget Sprint sprint);

    SprintWithoutDescriptionDTO mapSprintToSprintWithoutDescriptionDto(Sprint sprint);

    Sprint mapSprintDtoToSprint(SprintDTO sprintDTO);

}
