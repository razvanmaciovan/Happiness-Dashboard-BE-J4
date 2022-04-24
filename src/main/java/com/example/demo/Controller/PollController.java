package com.example.demo.Controller;

import com.example.demo.Models.Poll;
import com.example.demo.Services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/poll")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/get/{id}")
    public Optional<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPoll(id);
    }

    @GetMapping("/get/all")
    public List<Poll> getAllPolls() {
        return pollService.getAll();
    }

    @PostMapping("/add")
    public void addPoll(@RequestBody Poll poll) {
        pollService.addPoll(poll);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

    @PostMapping("/update/{id}")
    public void updatePoll(@PathVariable Long id, @RequestBody Poll poll) {
        pollService.updateInfo(id, poll);
    }

}
