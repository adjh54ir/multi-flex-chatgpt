package com.multiflex.multiflexchatgpt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiflex.multiflexchatgpt.config.ChatGPTConfig;
import com.multiflex.multiflexchatgpt.dto.CompletionRequestDto;
import com.multiflex.multiflexchatgpt.service.ChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    private final ChatGPTConfig chatGPTConfig;

    public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
        this.chatGPTConfig = chatGPTConfig;
    }

    /**
     * 사용 가능한 모델 리스트를 조회합니다.
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("[+] 모델 리스트를 조회합니다.");
        List<Map<String, Object>> objectList = new ArrayList<>();

        String url = "https://api.openai.com/v1/models";
        HttpHeaders headers = chatGPTConfig.httpHeaders();
        ResponseEntity<String> response = chatGPTConfig.restTemplate()
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


    /**
     * @return
     */
    @Override
    public Map<String, Object> isValidModel(String modelName) {

        String url = "https://api.openai.com/v1/models/" + modelName;
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        ResponseEntity<String> response = chatGPTConfig.restTemplate()
                .exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> result = new HashMap<>();
        try {
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * ChatGTP 검색
     *
     * @param completionRequestDto
     * @return
     */
    @Override
    public Map<String, Object> createCompletion(CompletionRequestDto completionRequestDto) {
        log.debug("[+] 프롬프트를 수행합니다.");
        String url = "https://api.openai.com/v1/completions";


        Map<String, Object> result = new HashMap<>();

        HttpHeaders headers = chatGPTConfig.httpHeaders();

        ObjectMapper om = new ObjectMapper();
        String requestBody = "";
        try {
            requestBody = om.writeValueAsString(completionRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<CompletionRequestDto> requestEntity = new HttpEntity<>(completionRequestDto, headers);

        // call the API
        ResponseEntity<String> response = chatGPTConfig.restTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);
        try {
            result = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void chatGpt3Api() {

    }

    @Override
    public void chatGpt3Service() {

    }

    @Override
    public void chatGpt3Client() {

    }

}
