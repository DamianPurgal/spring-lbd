package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.NBP.RateDTO;
import lbd.fissst.springlbd.DTO.NBP.RatesOfCurrencyDTO;

import java.time.LocalDate;
import java.util.List;

public interface NBPService {

    List<RateDTO> getExchangeRatesOfDay(LocalDate date);

    RatesOfCurrencyDTO getRatesOfCurrencyInPeriod(String currencyCode, LocalDate from, LocalDate to);

}
