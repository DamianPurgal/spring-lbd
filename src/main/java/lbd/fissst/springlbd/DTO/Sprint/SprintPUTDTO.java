package lbd.fissst.springlbd.DTO.Sprint;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SprintPUTDTO {

    protected String name;

    protected LocalDate dateStart;

    protected LocalDate dateEnd;

    protected String description;

    protected SprintStatus status;

}
