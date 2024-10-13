package com.weesftw.client;

import com.weesftw.MainConfig;
import com.weesftw.factory.GlobalFactory;
import com.weesftw.model.OpenAIData;
import com.weesftw.model.request.OpenAIRequest;
import com.weesftw.model.response.OpenAIResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
class OpenAIClient implements LLMClient<OpenAIData> {

    private final HttpClient client = HttpClient.newBuilder().build();
    private final HttpRequest.Builder request;

    OpenAIClient() {
        this.request = HttpRequest.newBuilder()
                .uri(MainConfig.INSTANCE.getLlmUrl())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer %s".formatted(MainConfig.INSTANCE.getLlmApiKey()));
    }

    @Override
    public OpenAIData proceed(String content) {
        try {
            final var request = new OpenAIRequest().message("user", content);
            final var bodyPublisher = HttpRequest.BodyPublishers.ofString(
                    GlobalFactory.INSTANCE.getMapper()
                            .writeValueAsString(request)
            );

            this.request.POST(bodyPublisher);
            if (log.isDebugEnabled()) {
                log.info("Requesting to OpenAI with content: (data: {}, content: {})", this.request.build().headers().map(), request);
            }

            var result = this.client.send(this.request.build(), HttpResponse.BodyHandlers.ofString());
            var body = GlobalFactory.INSTANCE.getMapper().readValue(result.body(), OpenAIResponse.class);
            if (log.isDebugEnabled()) {
                log.info("Received response from OpenAI: (data: {}, body: {} (body raw: {}))",
                        result.headers().map(), body, result.body());
            }

            if (result.statusCode() == 200) {
                return new OpenAIData(body.getChoices().getFirst().getMessage().getContent());
            }

            throw new RuntimeException("Failed to proceed with the request");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
