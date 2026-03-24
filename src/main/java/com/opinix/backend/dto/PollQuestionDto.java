package com.opinix.backend.dto;

import com.opinix.backend.model.QuestionRole;
import com.opinix.backend.model.QuestionType;

public class PollQuestionDto {
    private Long id;
    private String label;
    private QuestionType questionType;
    private int columnIndex;
    private String originalHeader;
    private QuestionRole role;


    public PollQuestionDto() {
    }

    public PollQuestionDto(Long id, int columnIndex, String originalHeader, String label, QuestionType questionType, QuestionRole role) {
        this.id = id;
        this.columnIndex = columnIndex;
        this.originalHeader = originalHeader;
        this.label = label;
        this.questionType = questionType;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return label;
    }

    public void setQuestionText(String questionText) {
        this.label = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    private QuestionRole getRole() {
        return role;
    }

    public void setRole(QuestionRole role) {
        this.role = role;
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
}
