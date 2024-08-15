package kz.diploma.exchange_rate.service;

import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.entity.RateEntity;

import java.time.LocalDate;
import java.util.HashMap;

public interface IntegrationService {
    RateEntity getExchangeRates(LocalDate date);
}
