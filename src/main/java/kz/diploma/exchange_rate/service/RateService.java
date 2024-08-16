package kz.diploma.exchange_rate.service;

import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.entity.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface RateService {
    List<RateEntity> findAll();

    RateEntity findById(LocalDate id);

    void save(RateEntity rateEntity);

    List<RateEntity> findTop(int count);

    void saveAll(List<RateEntity> list);

    RateEntity updateByDate(LocalDate date, Rate rate);

    void deleteByDate(LocalDate date);

    List<RateEntity> findAllByCreatedDateIn (List<LocalDate> dates);
}
