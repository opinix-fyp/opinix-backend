package com.opinix.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "poll_questions")
public class PollQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int columnIndex;
    private String originalHeader; //header from the CSV File
    private String label; //optional cleaned label of the header

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Enumerated(EnumType.STRING)
    private QuestionRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getColumnIndex() {
        return columnIndex;
    }
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }
    public String getOriginalHeader() {
        return originalHeader;
    }
    public void setOriginalHeader(String originalHeader) {
        this.originalHeader = originalHeader;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public QuestionType getType() {
        return type;
    }
    public void setType(QuestionType type) {
        this.type = type;
    }
    public QuestionRole getRole() {
        return role;
    }
    public void setRole(QuestionRole role) {
        this.role = role;
    }
    public Poll getPoll() {
        return poll;
    }
    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
