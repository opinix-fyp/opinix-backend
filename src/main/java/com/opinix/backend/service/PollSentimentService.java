package com.opinix.backend.service;

import com.opinix.backend.client.SentimentClient;
import com.opinix.backend.dto.SentimentInputItem;
import com.opinix.backend.dto.SentimentRequest;
import com.opinix.backend.model.*;
import com.opinix.backend.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollSentimentService {

    private final PollRepository pollRepository;
    private final SentimentClient sentimentClient; //interface to connect to ML component oh yeah baby

    public PollSentimentService(PollRepository pollRepository, SentimentClient sentimentClient) {
        this.pollRepository = pollRepository;
        this.sentimentClient = sentimentClient;
    }

    public SentimentResult analyzePoll(Long pollId){
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found"));

        List<SentimentInputItem> items = new ArrayList<>();

        //prefiltering feedback questions for optimization, we only want to analyze feedback questions (textual answers)
        List<PollQuestion> feedbackQuestions = poll.getQuestions().stream()
                .filter(q -> q.getRole() == QuestionRole.FEEDBACK)
                .collect(Collectors.toList());

        for (PollResponse response : poll.getResponses()) {
            for (PollQuestion q : feedbackQuestions) {
                PollAnswer ans = response.getAnswers().stream()
                        .filter(a -> a.getQuestion().getId().equals(q.getId()))
                        .findFirst()
                        .orElse(null);

                if (ans == null) continue;
                String text = ans.getRawValue();
                if(text == null || text.isBlank()) continue; //skip empty answers

                SentimentInputItem item = new SentimentInputItem();
                item.setResponseId(response.getId());
                item.setQuestionId(q.getId());
                item.setQuestionLabel(q.getLabel());
                item.setText(text);
                items.add(item);
            }
        }

        SentimentRequest request = new SentimentRequest();
        request.setPollId(poll.getId());
        request.setItems(items);

        return sentimentClient.analyze(request); //this is subject to change based on the ML component... harish... get to work...

        //todo continue ts...
    }
}
