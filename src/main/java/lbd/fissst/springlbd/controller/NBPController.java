package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.NBP.RateDTO;
import lbd.fissst.springlbd.DTO.NBP.RatesOfCurrencyDTO;
import lbd.fissst.springlbd.service.definition.NBPService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("nbp")
public class NBPController {

    private NBPService nbpService;

    @GetMapping("yesterday")
    public List<RateDTO> getExchangeRatesOfThePreviousDay(){
        return nbpService.getExchangeRatesOfDay(LocalDate.now().minusDays(2));
    }

    @GetMapping("lastTenDaysOfUSD")
    public RatesOfCurrencyDTO getLastTenDaysRatesOfUSD(){
        return nbpService.getRatesOfCurrencyInPeriod(
                "USD",
                LocalDate.now().minusDays(13),
                LocalDate.now().minusDays(2)
        );
    }


}
