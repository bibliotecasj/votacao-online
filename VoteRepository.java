package com.voting.platform.repository;

import com.voting.platform.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserIdAndPollId(Long userId, Long pollId);
    
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.option.id = :optionId")
    Integer countByOptionId(Long optionId);
}