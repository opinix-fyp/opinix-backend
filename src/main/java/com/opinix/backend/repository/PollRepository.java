package com.opinix.backend.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.opinix.backend.model.Poll;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    @EntityGraph(attributePaths = {
            "questions",
            "responses",
            "responses.answers",
            "responses.answers.question"
    })
    Optional<Poll> findDetailedById(Long id);

}
