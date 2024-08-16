package kz.diploma.exchange_rate.service.impl;

import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.entity.RateEntity;
import kz.diploma.exchange_rate.repository.RateRepository;
import kz.diploma.exchange_rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static kz.diploma.exchange_rate.util.Constants.*;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository repository;

    @Override
    public List<RateEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public RateEntity findById(LocalDate id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save (RateEntity rateEntity) {
        repository.save(rateEntity);
    }

    @Override
    public List<RateEntity> findTop(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "createdDate"));
        return repository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public void saveAll (List<RateEntity> list) {
        repository.saveAll(list);
    }

    @Override
    public RateEntity updateByDate(LocalDate date, Rate rate) {
        if( !(USD.equals(rate.getTitle()) || RUB.equals(rate.getTitle()) || EUR.equals(rate.getTitle()) ) ){
            throw new IllegalArgumentException (INCORRECT_RATE_TITLE_ERROR);
        }

        RateEntity rateInDb = repository.findById(date).orElseThrow();
        rateInDb.getRate().put(rate.getTitle(), rate);
        repository.save(rateInDb);
        return null;
    }

    @Override
    public List<RateEntity> findAllByCreatedDateIn(List<LocalDate> dates) {
        return repository.findAllByCreatedDateIn(dates);
    }

}
