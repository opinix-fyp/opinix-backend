package com.opinix.backend.model; //ts supposed to be in DTO, but who gaf... i gaf...

public class SentimentItemResult {
    private Long responseId;
    private Long questionId;
    private String text;
    private String sentiment; //pos, neg, neutral yada yada yada yada yada yada yada type shit
    private Double score;

    public SentimentItemResult() {
    }

    public SentimentItemResult(Long responseId, Long questionId, String text, String sentiment, Double score) {
        this.responseId = responseId;
        this.questionId = questionId;
        this.text = text;
        this.sentiment = sentiment;
        this.score = score;
    }

    //get and set
    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
