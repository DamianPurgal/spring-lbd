package lbd.fissst.springlbd.DTO.NBP;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RatesOfCurrencyDTO {
    private String code;

    private List<Rate> currencies;

    @Data
    @Builder
    public static class Rate{
        private LocalDate date;
        private Double bid;
        private Double ask;
    }
}
