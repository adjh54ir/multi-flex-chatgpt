package com.multiflex.multiflexchatgpt.service;

import com.multiflex.multiflexchatgpt.dto.CompletionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ChatGPTService
 * @since : 12/29/23
 */

@Service
public interface ChatGPTService {


    List<Map<String, Object>> modelList();

    Map<String, Object> createCompletion(CompletionRequestDto completionRequestDto);

    Map<String, Object> isValidModel(String modelName);


    void chatGpt3Api();

    void chatGpt3Service();

    void chatGpt3Client();

}
