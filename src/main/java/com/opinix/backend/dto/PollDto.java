package com.opinix.backend.dto;

import java.util.List;

public class PollDto {
    private Long id;
    private String title;
    private String source;
    private List<PollQuestionDto> questions;
    private List<PollResponseDto> responses;

    public PollDto() {
    }

    public PollDto(Long id, String title, String source,
                   List<PollQuestionDto> questions,
                   List<PollResponseDto> responses) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.questions = questions;
        this.responses = responses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<PollQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PollQuestionDto> questions) {
        this.questions = questions;
    }

    public List<PollResponseDto> getResponses() {
        return responses;
    }

    public void setResponses(List<PollResponseDto> responses) {
        this.responses = responses;
    }
}
