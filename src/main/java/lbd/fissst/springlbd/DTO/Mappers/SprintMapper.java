package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.Sprint.SprintDTO;
import lbd.fissst.springlbd.DTO.Sprint.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import org.mapstruct.Mapper;

@Mapper
public interface SprintMapper {

    SprintDTO mapToSprintDTO(Sprint sprint);

    SprintWithUserStoriesDTO mapToSprintWithUserStoriesDTO(Sprint sprint);
}
