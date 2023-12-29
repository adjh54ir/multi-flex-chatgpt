package com.multiflex.multiflexchatgpt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : CompletionRequestDto
 * @since : 12/29/23
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompletionRequestDto {

    private String model;

    private String prompt;

    private int temperature;

    private int max_tokens;

    private Object messages;

    CompletionRequestDto(String model, String prompt, int temperature, int max_tokens) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

}
