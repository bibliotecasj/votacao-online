package com.voting.platform.controller;

import com.voting.platform.model.Poll;
import com.voting.platform.repository.PollRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping
    public List<Poll> getAllActivePolls() {
        return pollRepository.findByStatus("ACTIVE");
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll savedPoll = pollRepository.save(poll);
        return ResponseEntity
                .created(URI.create("/api/polls/" + savedPoll.getId()))
                .body(savedPoll);
    }
}