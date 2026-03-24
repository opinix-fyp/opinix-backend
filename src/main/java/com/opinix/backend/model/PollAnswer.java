package com.opinix.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "poll_answers")
public class PollAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private PollResponse response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private PollQuestion question;

    @Column(columnDefinition = "TEXT")
    private String rawValue;

    private Double numericValue;
    private String choiceValue;

     //constructors
    public PollAnswer() {
    }

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public PollResponse getResponse() {
        return response;
    }
    public void setResponse(PollResponse response) {
        this.response = response;
    }

    public PollQuestion getQuestion() {
        return question;
    }
    public void setQuestion(PollQuestion question) {
        this.question = question;
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
