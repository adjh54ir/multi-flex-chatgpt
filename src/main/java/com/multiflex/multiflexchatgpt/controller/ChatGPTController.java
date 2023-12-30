package com.multiflex.multiflexchatgpt.controller;

import com.multiflex.multiflexchatgpt.dto.CompletionRequestDto;
import com.multiflex.multiflexchatgpt.service.ChatGPTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ChatGPTController
 * @since : 12/29/23
 */
@RestController
@RequestMapping(value = "/api/v1/chatGpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @GetMapping("/model")
    public ResponseEntity<Map<String, Object>> selectModel(@RequestParam(name = "modelName") String modelName) {
        Map<String, Object> result = chatGPTService.isValidModel(modelName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     */
    @PostMapping("/completion")
    public ResponseEntity<Map<String, Object>> selectCodeList(@RequestBody CompletionRequestDto completionRequestDto) {
        Map<String, Object> result = chatGPTService.createCompletion(completionRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * [API] ChatGPT 모델 리스트를 조회합니다.
     */
    @PostMapping("/modelList")
    public ResponseEntity<List<Map<String, Object>>> selectCodeList() {
        List<Map<String, Object>> result = chatGPTService.modelList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
