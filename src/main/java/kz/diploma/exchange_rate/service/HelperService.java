package kz.diploma.exchange_rate.service;

import jakarta.xml.bind.JAXBException;

import java.time.LocalDate;

public interface HelperService {
    <T> T unmarshalData(String response, Class<T> responseType) throws JAXBException;
    String formatLocalDate(LocalDate date);
}
