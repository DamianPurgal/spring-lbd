package lbd.fissst.springlbd.DTO.Sprint;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SprintWithoutDescriptionDTO {

    protected String name;

    protected LocalDate dateStart;

    protected LocalDate dateEnd;

    protected SprintStatus status;
}
