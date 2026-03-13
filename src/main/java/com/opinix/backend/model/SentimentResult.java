package com.opinix.backend.model; //ts supposed to be in DTO, but who gaf... i gaf...

import java.util.List;

public class SentimentResult {
    private Long pollId;
    private List<SentimentItemResult> results;

    public SentimentResult() {
    }

    public SentimentResult(Long pollId, List<SentimentItemResult> results) {
        this.pollId = pollId;
        this.results = results;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public List<SentimentItemResult> getResults() {
        return results;
    }

    public void setResults(List<SentimentItemResult> results) {
        this.results = results;
    }
}
