package com.opinix.backend.dto;

public class PollAnswerDto {
    private Long id;
    private Long questionId;
    private String questionLabel;
    private String rawValue;
    private Double numericValue;
    public String choiceValue;

    public PollAnswerDto() {
    }

    public PollAnswerDto(Long id, Long questionId, String questionLabel, String rawValue, Double numericValue, String choiceValue) {
        this.id = id;
        this.questionId = questionId;
        this.questionLabel = questionLabel;
        this.rawValue = rawValue;
        this.numericValue = numericValue;
        this.choiceValue = choiceValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public Double getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(Double numericValue) {
        this.numericValue = numericValue;
    }

    public String getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }
}
