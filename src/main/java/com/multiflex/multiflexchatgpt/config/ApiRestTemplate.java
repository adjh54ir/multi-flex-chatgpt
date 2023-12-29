package com.multiflex.multiflexchatgpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : RestTemplate
 * @since : 12/29/23
 */
@Configuration
public class ApiRestTemplate {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
