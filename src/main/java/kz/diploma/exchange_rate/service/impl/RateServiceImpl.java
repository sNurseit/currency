package kz.diploma.exchange_rate.service.impl;

import kz.diploma.exchange_rate.entity.RateEntity;
import kz.diploma.exchange_rate.repository.RateRepository;
import kz.diploma.exchange_rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        Pageable pageable = PageRequest.of(0,count);
        return repository.findAll(pageable).getContent();
    }
}
