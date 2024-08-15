package kz.diploma.exchange_rate.controller;


import kz.diploma.exchange_rate.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/integration")
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationService integrationService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(name = "date", required = false) LocalDate date){
        return ResponseEntity.ok(integrationService.getExchangeRates(date));
    }
}
