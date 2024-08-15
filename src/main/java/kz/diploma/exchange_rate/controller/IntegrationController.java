package kz.diploma.exchange_rate.controller;


import kz.diploma.exchange_rate.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/integration")
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationService integrationService;

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(name = "date", required = false) LocalDate date){
        return ResponseEntity.ok(integrationService.getExchangeRates(date));
    }
}
