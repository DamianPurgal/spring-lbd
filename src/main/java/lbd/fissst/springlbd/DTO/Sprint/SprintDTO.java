package lbd.fissst.springlbd.DTO.Sprint;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {

    protected String name;

    protected LocalDate dateStart;

    protected LocalDate dateEnd;

    protected String description;

    protected SprintStatus status;

}
