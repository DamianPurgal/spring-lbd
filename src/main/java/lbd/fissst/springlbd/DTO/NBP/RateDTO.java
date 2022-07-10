package lbd.fissst.springlbd.DTO.NBP;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateDTO {
    String currency;
    String code;
    Double bid;
    Double ask;
}
