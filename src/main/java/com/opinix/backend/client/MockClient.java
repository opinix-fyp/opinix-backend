package com.opinix.backend.client;

import com.opinix.backend.dto.SentimentInputItem;
import com.opinix.backend.dto.SentimentRequest;
import com.opinix.backend.model.SentimentItemResult;
import com.opinix.backend.model.SentimentResult;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@org.springframework.context.annotation.Profile("mock") //so that this is used when we are testing it out. when harish gets his work done, we switch to ml profile
public class MockClient implements SentimentClient{

    @Override
    public SentimentResult analyze(SentimentRequest request) {
        List<SentimentItemResult> results = new ArrayList<>();

        for (SentimentInputItem item : request.getItems()) {
            String text = item.getText() == null ? "" : item.getText().toLowerCase();

            String sentiment = "NEUTRAL";
            double score = 0.5;

            if(text.contains("good") || text.contains("great") || text.contains("excellent")) {
                sentiment = "POSITIVE";
                score = 0.9;
            } else if(text.contains("bad") || text.contains("terrible") || text.contains("awful")) {
                sentiment = "NEGATIVE";
                score = 0.1; //cgpt says 0.9 also.. which is weird. gotta ask it later
            }

            SentimentItemResult itemResult = new SentimentItemResult(
                    item.getResponseId(),
                    item.getQuestionId(),
                    item.getText(),
                    sentiment,
                    score
            );

            results.add(itemResult);
        }

        return new SentimentResult(request.getPollId(), results);
    }
}
