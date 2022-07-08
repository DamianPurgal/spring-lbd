package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.SprintDTO;
import lbd.fissst.springlbd.DTO.SprintWithUserStoriesDTO;
import lbd.fissst.springlbd.Entity.Sprint;
import org.mapstruct.Mapper;

@Mapper
public interface SprintMapper {

    SprintDTO mapToSprintDTO(Sprint sprint);

    SprintWithUserStoriesDTO mapToSprintWithUserStoriesDTO(Sprint sprint);
}
