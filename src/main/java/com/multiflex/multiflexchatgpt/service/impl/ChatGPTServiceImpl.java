package com.multiflex.multiflexchatgpt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiflex.multiflexchatgpt.config.ApiRestTemplate;
import com.multiflex.multiflexchatgpt.dto.CompletionRequestDto;
import com.multiflex.multiflexchatgpt.service.ChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ChatGPTServiceImpl
 * @since : 12/29/23
 */
@Slf4j
@Service
public class ChatGPTServiceImpl implements ChatGPTService {


    @Value("${openai.secret-key}")
    private String secretKey;

    @Value("${openai.model}")
    private String model;


    private final ApiRestTemplate restTemplate;


    public ChatGPTServiceImpl(ApiRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> objectList = new ArrayList<>();

        String url = "https://api.openai.com/v1/models";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.restTemplate()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        ObjectMapper om = new ObjectMapper();

        try {
            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<>() {
            });
            // data 내 object 정보 출력
            objectList = (List<Map<String, Object>>) data.get("data");
            for (Map<String, Object> object : objectList) {
                log.debug("ID: " + object.get("id"));
                log.debug("Object: " + object.get("object"));
                log.debug("Created: " + object.get("created"));
                log.debug("Owned By: " + object.get("owned_by"));
            }

            log.debug(response.getBody());
        } catch (JsonMappingException e) {
            log.debug("JsonMappingException :: " + e.getMessage());
        } catch (JsonProcessingException e) {
            log.debug("RuntimeException :: " + e.getMessage());
        }
        return objectList;
    }

    @Override
    public Map<String, Object> createCompletion(CompletionRequestDto completionRequestDto) {

        log.debug("[+] 프롬프트를 수행합니다.");

        String url = "https://api.openai.com/v1/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);


        // call the API
        String response = restTemplate.restTemplate().postForObject(url, completionRequestDto, String.class);
        System.out.println("result :: " + response);

        ObjectMapper om = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = om.writeValueAsString(completionRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<Object> response = restTemplate.restTemplate().postForEntity(url, requestEntity, Object.class);

//        log.debug("결과는 :: " + response.getBody());


        return null;
    }

}
