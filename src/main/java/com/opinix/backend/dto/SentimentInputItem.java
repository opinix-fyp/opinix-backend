package com.opinix.backend.dto;

public class SentimentInputItem {
    private Long responseId;
    private Long questionId;
    private String questionLabel;
    private String text;

    public SentimentInputItem() {
    }

    public SentimentInputItem(Long responseId, Long questionId, String questionLabel, String text) {
        this.responseId = responseId;
        this.questionId = questionId;
        this.questionLabel = questionLabel;
        this.text = text;
    }

    //get set go...
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

    public String getQuestionLabel() {
        return questionLabel;
    }
    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
