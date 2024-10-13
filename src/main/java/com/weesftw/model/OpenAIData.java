package com.weesftw.model;

import lombok.ToString;

@ToString(callSuper = true)
public class OpenAIData extends LLMData {

    public OpenAIData(String content) {
        super(content);
    }
}
