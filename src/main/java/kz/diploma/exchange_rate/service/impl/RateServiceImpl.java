package kz.diploma.exchange_rate.service.impl;

import kz.diploma.exchange_rate.entity.RateEntity;
import kz.diploma.exchange_rate.repository.RateRepository;
import kz.diploma.exchange_rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository repository;

    @Override
    public List<RateEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public RateEntity save (RateEntity rateEntity) {
        return repository.save(rateEntity);
    }
}
