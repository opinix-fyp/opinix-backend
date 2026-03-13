package com.opinix.backend.dto;

import com.opinix.backend.dto.PollAnswerDto;

import java.util.List;

public class PollResponseDto {
    private Long id;
    private Integer rowIndex;
    private List<PollAnswerDto> answers;

    public PollResponseDto() {
    }

    public PollResponseDto(Long id, Integer rowIndex, List<PollAnswerDto> answers) {
        this.id = id;
        this.rowIndex = rowIndex;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<PollAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<PollAnswerDto> answers) {
        this.answers = answers;
    }
}
