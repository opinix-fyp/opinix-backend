package com.opinix.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //title of the actual poll itself ie: "Event A Feedback"
    private String source; //source of the poll ie: "GOOGLE_FORMS"

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollQuestion> questions = new ArrayList<>();

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollResponse> responses = new ArrayList<>();

    //helper functions
    public void addQuestion(PollQuestion question) {
        questions.add(question);
        question.setPoll(this);
    }

    public void addResponse(PollResponse response) {
        responses.add(response);
        response.setPoll(this);
    }

    //getter and setters
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
    public List<PollQuestion> getQuestions() {
        return questions;
    }
    public void setQuestions(List<PollQuestion> questions) {
        this.questions = questions;
    }
    public List<PollResponse> getResponses() {
        return responses;
    }
    public void setResponses(List<PollResponse> responses) {
        this.responses = responses;
    }

}
