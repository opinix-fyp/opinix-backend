package com.opinix.backend;

import com.opinix.backend.dto.PollAnswerDto;
import com.opinix.backend.dto.PollDto;
import com.opinix.backend.dto.PollQuestionDto;
import com.opinix.backend.dto.PollResponseDto;
import com.opinix.backend.model.Poll;
import com.opinix.backend.model.PollAnswer;
import com.opinix.backend.model.PollQuestion;
import com.opinix.backend.model.PollResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PollMapper {

    private PollMapper() {
    }

    public static PollDto toDto(Poll poll){
        if (poll == null) {
            return null;
        }

        List<PollQuestionDto> questionDtos = new ArrayList<>();
        if (poll.getQuestions() != null) {
            poll.getQuestions().stream()
                    .sorted(Comparator.comparingInt(PollQuestion::getColumnIndex))
                    .map(PollMapper::toQuestionDto)
                    .forEach(questionDtos::add);
        }

        List<PollResponseDto> responseDtos = new ArrayList<>();
        if (poll.getResponses() != null) {
            poll.getResponses().stream()
                    .sorted(Comparator.comparingInt(PollResponse::getRowIndex))
                    .map(PollMapper::toResponseDto)
                    .forEach(responseDtos::add);
        }

        return new PollDto(
                poll.getId(),
                poll.getTitle(),
                poll.getSource(),
                questionDtos,
                responseDtos
        );
    }

    public static PollQuestionDto toQuestionDto(PollQuestion question) {
        if (question == null) {
            return null;
        }
        return new PollQuestionDto(
                question.getId(),
                question.getColumnIndex(),
                question.getOriginalHeader(),
                question.getLabel(),
                question.getType(),
                question.getRole()
        );
    }

    public static PollResponseDto toResponseDto(PollResponse response) {
        if (response == null) {
            return null;
        }
        List<PollAnswerDto> answerDtos = new ArrayList<>();
        if (response.getAnswers() != null) {
            response.getAnswers().stream()
                    .sorted(Comparator.comparingInt(a -> a.getQuestion().getColumnIndex()))
                    .map(PollMapper::toAnswerDto)
                    .forEach(answerDtos::add);
        }
        return new PollResponseDto(
                response.getId(),
                response.getRowIndex(),
                answerDtos
        );
    }

    public static PollAnswerDto toAnswerDto(PollAnswer answer) {
        if (answer == null) {
            return null;
        }

        String questionLabel = null;
        Long questionId = null;

        if (answer.getQuestion() != null) {
            questionId = answer.getQuestion().getId();
            questionLabel = answer.getQuestion().getLabel();
        }

        return new PollAnswerDto(
                answer.getId(),
                questionId,
                questionLabel,
                answer.getRawValue(),
                answer.getNumericValue(),
                answer.getChoiceValue()
        );
    }

}
