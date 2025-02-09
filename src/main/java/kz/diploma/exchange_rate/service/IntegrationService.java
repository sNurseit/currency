package kz.diploma.exchange_rate.service;

import kz.diploma.exchange_rate.entity.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface IntegrationService {
    List<RateEntity> getExchangeRates(LocalDate startDate, LocalDate endDate);
}
