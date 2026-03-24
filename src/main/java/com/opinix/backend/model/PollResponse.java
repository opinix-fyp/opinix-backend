package com.opinix.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poll_responses")
public class PollResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowIndex;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollAnswer> answers = new ArrayList<>();

    //funcs
    public void addAnswer(PollAnswer a){
        answers.add(a);
        a.setResponse(this);
    }

    //get set
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }
    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<PollAnswer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<PollAnswer> answers) {
        this.answers = answers;
    }

    public int getRowIndex() {
        return rowIndex;
    }
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
