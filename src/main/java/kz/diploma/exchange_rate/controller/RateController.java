package kz.diploma.exchange_rate.controller;

import kz.diploma.exchange_rate.dto.Rate;
import kz.diploma.exchange_rate.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/rate")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(rateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")String id){
        try {
            LocalDate date = LocalDate.parse(id);
            return ResponseEntity.ok(rateService.findById(date));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format: " + id);
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> findTop (@RequestParam(name = "count", defaultValue = "10") Integer count){
        return ResponseEntity.ok(rateService.findTop(count));
    }

    @PutMapping("/{date}")
    public ResponseEntity<?> update(@PathVariable("date") LocalDate date, @RequestBody Rate rate){
        return ResponseEntity.ok(rateService.updateByDate(date, rate));
    }

}
