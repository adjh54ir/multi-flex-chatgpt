package com.multiflex.multiflexchatgpt.service;

import com.multiflex.multiflexchatgpt.dto.CompletionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT 서비스 인터페이스
 *
 * @author : lee
 * @fileName : ChatGPTService
 * @since : 12/29/23
 */

@Service
public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    Map<String, Object> prompt(CompletionRequestDto completionRequestDto);

    Map<String, Object> isValidModel(String modelName);

}
