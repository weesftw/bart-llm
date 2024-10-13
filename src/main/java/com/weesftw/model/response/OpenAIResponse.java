package com.weesftw.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenAIResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String objectType;

    @JsonProperty("created")
    private long created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    @JsonProperty("choices")
    private List<Choice> choices;

    @JsonProperty("usage")
    private Usage usage;

    @JsonProperty("error")
    private ErrorDetail error;

    @Data
    public static class ErrorDetail {

        @JsonProperty("message")
        private String message;

        @JsonProperty("type")
        private String type;

        @JsonProperty("param")
        private String param;

        @JsonProperty("code")
        private String code;
    }

    @Data
    public static class Choice {

        @JsonProperty("index")
        private int index;

        @JsonProperty("message")
        private Message message;

        @JsonProperty("logprobs")
        private Object logprobs;

        @JsonProperty("finish_reason")
        private String finishReason;

        @Data
        public static class Message {

            @JsonProperty("role")
            private String role;

            @JsonProperty("content")
            private String content;
        }
    }

    @Data
    public static class Usage {

        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;

        @JsonProperty("completion_tokens_details")
        private CompletionTokensDetails completionTokensDetails;

        @Data
        public static class CompletionTokensDetails {

            @JsonProperty("reasoning_tokens")
            private int reasoningTokens;
        }
    }
}
