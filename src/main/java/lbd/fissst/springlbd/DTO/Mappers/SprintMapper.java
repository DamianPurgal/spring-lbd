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

    SprintDTO mapToSprintDTO(Sprint sprint);

    SprintWithUserStoriesDTO mapToSprintWithUserStoriesDTO(Sprint sprint);

    Sprint mapSprintPUTDTOtoSprint(SprintPUTDTO sprintPUTDTO, @MappingTarget Sprint sprint);

    SprintWithoutDescriptionDTO mapSprintToSprintWithoutDescription(Sprint sprint);
}
