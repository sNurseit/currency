package kz.diploma.exchange_rate.controller;

import kz.diploma.exchange_rate.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RateControllerTest {
    @Mock
    private RateService rateService;

    @InjectMocks
    private RateController rateController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(rateController).build();
    }

    @Test
    void findByIdTest() throws Exception {
        mockMvc.perform(get("/api/rate/2024-08-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100));
    }
}
