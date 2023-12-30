package com.multiflex.multiflexchatgpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : RestTemplate
 * @since : 12/29/23
 */
@Configuration
public class ChatGPTConfig {


    @Value("${openai.secret-key}")
    private String secretKey;

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().add("Authorization", "Bearer " + secretKey);
//            return execution.execute(request, body);
//        });
        return restTemplate;
    }


    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;

    }
}
