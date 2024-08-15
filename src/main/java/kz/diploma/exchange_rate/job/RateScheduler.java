package kz.diploma.exchange_rate.job;


import kz.diploma.exchange_rate.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RateScheduler {
    private final IntegrationService integrationService;

    @Scheduled(cron = "0 52 01 * * ?", zone = "GMT+5")
    void schedulerForSmsMonitoring() {
        log.info("Cron started");
        integrationService.getExchangeRates(null);
        log.info("Cron ended");
    }
}
