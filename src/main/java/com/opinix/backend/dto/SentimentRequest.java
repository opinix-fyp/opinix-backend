package com.opinix.backend.dto;

import java.util.List;

public class SentimentRequest {
    private Long pollId;
    private List<SentimentInputItem> items;

    public SentimentRequest() {
    }

    public SentimentRequest(Long pollId, List<SentimentInputItem> items) {
        this.pollId = pollId;
        this.items = items;
    }

    //get set go once again...
    public Long getPollId() {
        return pollId;
    }
    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public List<SentimentInputItem> getItems() {
        return items;
    }
    public void setItems(List<SentimentInputItem> items) {
        this.items = items;
    }
}
