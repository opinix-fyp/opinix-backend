package com.opinix.backend.repository;

import com.opinix.backend.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long> {
    List<PollQuestion> findByPollIdOrderByColumnIndexAsc(Long pollId);
}
