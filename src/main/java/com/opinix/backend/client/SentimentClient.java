package com.opinix.backend.client;

import com.opinix.backend.dto.SentimentRequest;
import com.opinix.backend.model.SentimentResult;

public interface SentimentClient {
    SentimentResult analyze(SentimentRequest request);
}
