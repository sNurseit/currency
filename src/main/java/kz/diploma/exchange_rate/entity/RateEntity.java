package kz.diploma.exchange_rate.entity;

import jakarta.persistence.*;
import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.mapper.MapToStringConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "rate_history")
@NoArgsConstructor
public class RateEntity {


    @Id
    @Column(name = "created_date", nullable = false, columnDefinition = "DATE", unique = true)
    private LocalDate createdDate;

    @Column(name = "rate", nullable = false, columnDefinition = "TEXT")
    @Convert(converter = MapToStringConverter.class)
    private Map<String, Rate> rate;
}
