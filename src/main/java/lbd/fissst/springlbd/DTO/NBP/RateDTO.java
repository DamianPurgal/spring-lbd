package lbd.fissst.springlbd.DTO.NBP;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateDTO {
    private String currency;
    private String code;
    private Double bid;
    private Double ask;
}
