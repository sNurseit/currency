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

@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {

    @Override
    public <T> T unmarshalData(String response, Class<T> responseType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(responseType);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return responseType.cast(unmarshaller.unmarshal(new StringReader(response)));
    }

    @Override
    public String formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }
}
