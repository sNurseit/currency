package kz.diploma.exchange_rate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    };

}