package com.opinix.backend.repository;

import com.opinix.backend.model.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {
    List<PollAnswer> findByResponseId(Long responseId);
    List<PollAnswer> findByQuestionId(Long questionId);
}
