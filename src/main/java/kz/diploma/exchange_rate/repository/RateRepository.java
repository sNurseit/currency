package kz.diploma.exchange_rate.repository;

import kz.diploma.exchange_rate.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, String> {
}
