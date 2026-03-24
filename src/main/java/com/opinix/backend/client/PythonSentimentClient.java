package com.opinix.backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.opinix.backend.dto.SentimentRequest;
import com.opinix.backend.model.SentimentResult;

@Component
@org.springframework.context.annotation.Profile("ml")
public class PythonSentimentClient implements SentimentClient {

    private final RestTemplate restTemplate;
    private final String sentimentApiUrl;

    public PythonSentimentClient(
        RestTemplateBuilder restTemplateBuilder,
        @Value("${spring.ml.sentiment.url}") String sentimentApiUrl
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.sentimentApiUrl = sentimentApiUrl;
    }

    @Override
    public SentimentResult analyze(SentimentRequest request) {
        return restTemplate.postForObject(
                sentimentApiUrl,
                request,
                SentimentResult.class
        );
    }
}
