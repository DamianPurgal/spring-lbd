package lbd.fissst.springlbd.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import lbd.fissst.springlbd.DTO.NBP.RateDTO;
import lbd.fissst.springlbd.DTO.NBP.RatesOfCurrencyDTO;
import lbd.fissst.springlbd.service.definition.NBPService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NBPServiceImpl implements NBPService {

    private final static String baseUrl = "http://api.nbp.pl/api/";
    private final static String format = "?format=json";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<RateDTO> getExchangeRatesOfDay(LocalDate date) {

        String url = "exchangerates/tables/C/";

        String completeURL = baseUrl
                + url
                + date.toString()
                + format;

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(completeURL, JsonNode.class);

        List<RateDTO> rates = new ArrayList<>();
        for(JsonNode node : response.getBody().findValue("rates")){
            rates.add(
                    RateDTO.builder()
                            .currency(node.path("currency").asText())
                            .code(node.path("code").asText())
                            .bid(node.path("bid").asDouble())
                            .ask(node.path("ask").asDouble())
                            .build()
            );
        }

        return rates;
    }

    @Override
    public RatesOfCurrencyDTO getRatesOfCurrencyInPeriod(String currencyCode, LocalDate from, LocalDate to) {
        String url = "exchangerates/rates/C/";

        String completeURL = baseUrl
                + url
                + currencyCode
                + "/" + from.toString()
                + "/" + to.toString()
                + format;

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(
                completeURL
                , JsonNode.class);


        List<RatesOfCurrencyDTO.Rate> rates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(JsonNode node : response.getBody().findValue("rates")){
            rates.add(
                    RatesOfCurrencyDTO.Rate.builder()
                            .date(LocalDate.parse(node.path("effectiveDate").textValue(), formatter))
                            .ask(node.path("ask").asDouble())
                            .bid(node.path("bid").asDouble())
                            .build()
            );
        }
        return RatesOfCurrencyDTO.builder()
                .code(currencyCode)
                .currencies(rates)
                .build();
    }
}
