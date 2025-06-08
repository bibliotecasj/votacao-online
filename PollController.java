package com.voting.platform.controller;

import com.example.plataforma.votacao.model.Vote;
import com.voting.platform.model.Poll;
import com.voting.platform.repository.PollRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping("/{pollId}/options")
public ResponseEntity<List<Option>> getPollOptions(@PathVariable Long pollId) {
    return ResponseEntity.ok(
        optionRepository.findByPollId(pollId)
    );
}

@PostMapping("/{pollId}/vote")
public ResponseEntity<?> vote(
    @PathVariable Long pollId,
    @RequestParam Long optionId,
    @AuthenticationPrincipal User user
) {
    // Verifica se a enquete está ativa
    Poll poll = pollRepository.findById(pollId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found"));

    if (!"ACTIVE".equals(poll.getStatus())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Poll is closed");
    }

    // Verifica se o usuário já votou
    if (voteRepository.existsByUserIdAndPollId(user.getId(), pollId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already voted in this poll");
    }

    Option option = optionRepository.findById(optionId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Option not found"));

    // Registra o voto
    Vote vote = new Vote();
    vote.setUser(user);
    vote.setOption(option);
    vote.setPoll(poll);
    voteRepository.save(vote);

    // Atualiza contagem
    option.setVoteCount(option.getVoteCount() + 1);
    optionRepository.save(option);

    return ResponseEntity.ok().build();
}

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll savedPoll = pollRepository.save(poll);
        return ResponseEntity
                .created(URI.create("/api/polls/" + savedPoll.getId()))
                .body(savedPoll);
    }
}

