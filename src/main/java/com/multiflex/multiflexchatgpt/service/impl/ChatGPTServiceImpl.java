package com.multiflex.multiflexchatgpt.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.multiflex.multiflexchatgpt.config.ApiRestTemplate;
import com.multiflex.multiflexchatgpt.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ChatGPTServiceImpl
 * @since : 12/29/23
 */
@Service
public class ChatGPTServiceImpl implements ChatGPTService {


    @Value("${openai.secret-key}")
    private String secretKey;


    private final ApiRestTemplate restTemplate;


    public ChatGPTServiceImpl(ApiRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public JSONPObject selectChatGPTModelList() {
        System.out.println("[+] 모델 리스트를 조회합니다.");

        String url = "https://api.openai.com/v1/models";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        ResponseEntity<Object> response = restTemplate.restTemplate()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        System.out.println(response.getBody());

        return null;
    }
}
