package com.example.demo.Services;

import com.example.demo.Models.Poll;
import com.example.demo.repo.PollRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepo pollRepo;

    @Autowired
    public PollService(PollRepo pollRepo) {
        this.pollRepo = pollRepo;
    }

    public List<Poll> getAll() {
        return pollRepo.findAll();
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepo.findById(id);
    }

    public void addPoll(Poll poll) {
        pollRepo.save(poll);
    }

    public void deletePoll(Long id) {
        pollRepo.deleteById(id);
    }

    public void updateInfo(Long id, Poll poll) {
        Poll updatedPoll = pollRepo.getById(id);
        updateInfo(updatedPoll, poll);
        pollRepo.save(updatedPoll);
    }

    private void updateInfo(@NotNull Poll originalPoll, @NotNull Poll newPoll) {
        originalPoll.setTopic_id(newPoll.getTopic_id());
        originalPoll.setDateOfCreation(newPoll.getDateOfCreation());
        originalPoll.setDateOfClosing(newPoll.getDateOfClosing());
        originalPoll.setStatus(newPoll.getStatus());
    }
}
