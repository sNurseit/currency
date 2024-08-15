package kz.diploma.exchange_rate.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.dto.Rates;
import kz.diploma.exchange_rate.entity.RateEntity;
import kz.diploma.exchange_rate.mapper.MapToStringConverter;
import kz.diploma.exchange_rate.service.HelperService;
import kz.diploma.exchange_rate.service.IntegrationService;
import kz.diploma.exchange_rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kz.diploma.exchange_rate.util.Constants.*;


@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final RestTemplate restTemplate;
    private final RateService rateService;
    private final HelperService helperService;

    @Value("${integration.url}")
    private String url;

    @Override
    public RateEntity getExchangeRates(LocalDate date) {
        try {
            LocalDate targetDate = date != null ? date : LocalDate.now();
            String formattedDate = helperService.formatLocalDate(targetDate);

            String response = fetchRatesFromApi(formattedDate);
            return parseAndSaveRates(response, targetDate);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Создает URL-адрес запроса API, используя указанную дату, и отправляет запрос GET для получения курсов.
     *
     * @param  formattedDate дата, на которую запрашиваются курсы валют, в формате строки (например, "дд.ММ.гггг").
     * @return  ответ в формате JSON в виде строки из API, содержащей обменные курсы на указанную дату.
     */
    private String fetchRatesFromApi(String formattedDate) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(F_DATE, formattedDate);
        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    private RateEntity parseAndSaveRates(String response, LocalDate date) throws JAXBException {

        Rates rates = helperService.unmarshalData(response, Rates.class);
        Map<String, Rate> selectedRates = filterRelevantRates(rates.getRates());

        RateEntity rateEntity = new RateEntity();
        rateEntity.setRate(selectedRates);
        rateEntity.setCreatedDate(date);

        rateService.save(rateEntity);

        return rateEntity;

    }

    private Map<String, Rate> filterRelevantRates(List<Rate> rateList) {
        return rateList.stream()
                .filter(rate -> USD.equals(rate.getTitle()) || RUB.equals(rate.getTitle()))
                .collect(Collectors.toMap(Rate::getTitle, rate -> rate));
    }

}
