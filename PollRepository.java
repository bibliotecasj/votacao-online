package com.voting.platform.repository;

import com.voting.platform.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByStatus(String status);
    List<Poll> findByCreatorId(Long creatorId);
}