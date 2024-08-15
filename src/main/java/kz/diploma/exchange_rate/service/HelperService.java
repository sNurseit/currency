package kz.diploma.exchange_rate.service;

import jakarta.xml.bind.JAXBException;

import java.time.LocalDate;

public interface HelperService {

    /**
     * Преобразует XML-строку в объект указанного типа
     *
     * @param xmlString XML в виде строки
     * @param responseType тип класса, к которому должен быть преобразован XML строка
     * @return  экземпляр указанного типа с данными
     * @throws JAXBException выбарсываемое исключение при ошибке переобразования
     */
    <T> T unmarshalData(String xmlString, Class<T> responseType) throws JAXBException;
    String formatLocalDate(LocalDate date);
}
