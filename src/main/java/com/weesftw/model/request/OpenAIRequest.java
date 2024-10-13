package com.weesftw.model.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpenAIRequest {

    private final String model = "openai/gpt-3.5-turbo";
    private final List<Messages> messages = new ArrayList<>(1);

    public OpenAIRequest message(String role, String content) {
        this.messages.add(new Messages(role, content));
        return this;
    }

    @Data
    public static class Messages {

        private final String role;
        private final String content;

        public Messages(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
