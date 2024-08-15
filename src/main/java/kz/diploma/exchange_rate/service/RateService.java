package kz.diploma.exchange_rate.service;

import kz.diploma.exchange_rate.entity.RateEntity;

import java.util.List;

public interface RateService {
    List<RateEntity> findAll();

    RateEntity save(RateEntity rateEntity);
}
