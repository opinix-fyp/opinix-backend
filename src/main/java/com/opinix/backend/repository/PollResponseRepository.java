package com.opinix.backend.repository;

import com.opinix.backend.model.PollResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollResponseRepository extends JpaRepository<PollResponse, Long> {
    List<PollResponse> findByPollIdOrderByRowIndexAsc(Long pollId);
}
