package com.weesftw.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class LLMData {

    protected final String content;

    protected LLMData(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
