package kz.diploma.exchange_rate.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import kz.diploma.exchange_rate.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static kz.diploma.exchange_rate.util.Constants.START_DATE_CANNOT_BE_GREATER_END_DATE_ERROR;

@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {

    @Override
    public <T> T unmarshalData(String xmlString, Class<T> responseType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(responseType);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return responseType.cast(unmarshaller.unmarshal(new StringReader(xmlString)));
    }

    @Override
    public String formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    @Override
    public List<LocalDate> getLocalDateList (LocalDate startDate, LocalDate endDate) {
        if(endDate.isBefore(startDate)){
            throw new IllegalArgumentException (START_DATE_CANNOT_BE_GREATER_END_DATE_ERROR);
        }
        LocalDate localDate = LocalDate.now();
        List<LocalDate> dateList = new ArrayList<>();
        while (!startDate.isAfter(endDate) && !startDate.isAfter(localDate)) {
            dateList.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return dateList;
    }
}
