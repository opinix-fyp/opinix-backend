package com.opinix.backend.model; //ts supposed to be in DTO, but who gaf... i gaf...

import java.util.List;

public class SentimentResult {
    private Long pollId;
    private List<SentimentItemResult> results;

    private String summary;

    public SentimentResult() {
    }

    public SentimentResult(Long pollId, List<SentimentItemResult> results, String summary) {
        this.pollId = pollId;
        this.results = results;
        this.summary = summary;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
