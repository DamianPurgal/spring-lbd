package lbd.fissst.springlbd.DTO;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SprintDTO {

    protected String name;

    protected LocalDate dateStart;

    protected LocalDate dateEnd;

    protected String description;

    protected SprintStatus status;

}
