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

    /* Returns a List of Polls from the DB */
    public List<Poll> getAll() {
        return pollRepo.findAll();
    }

    /* Returns an Optional containing the found Poll with the given ID or an Empty one */
    public Optional<Poll> getPoll(Long id) {
        return pollRepo.findById(id);
    }

    /* Adds a Poll to the DB */
    public void addPoll(Poll poll) {
        poll.setDateOfCreation(pollRepo.getDBTimestamp());
        poll.setDateOfClosing(pollRepo.getDateOfClosing(poll.getDaysTillClosing()));
        pollRepo.save(poll);
    }

    /* Deletes a Poll from the DB with given ID */
    public boolean deletePoll(Long id) {
        Optional<Poll> pollById = pollRepo.findById(id); // we search for the Poll with the given ID
        if (pollById.isPresent()) { // if we found it
            pollRepo.deleteById(id); // we delete it
            return true;
        }
        return false; // if there was no Poll with the given ID we return false
    }

    /* Updates a Poll with the given ID */
    public Optional<Poll> updatePoll(Long id, Poll newPoll) {
        Optional<Poll> updatedPoll = pollRepo.findById(id); // we search for the Poll with the given ID
        if (updatedPoll.isPresent()) { // if we found it
            updatePollInfo(updatedPoll.get(), newPoll); // we update its info
            pollRepo.save(updatedPoll.get()); // and we save it in the DB
        }
        return updatedPoll; // we return an Optional containing the updated Poll or an Empty one
    }

    /* Helper Method that updates a Poll */
    private void updatePollInfo(@NotNull Poll originalPoll, @NotNull Poll newPoll) {
        originalPoll.setTopic_id(newPoll.getTopic_id());
        originalPoll.setDateOfCreation(newPoll.getDateOfCreation());
        originalPoll.setDateOfClosing(newPoll.getDateOfClosing());
        originalPoll.setStatus(newPoll.getStatus());
    }

    public List<Poll> getMostRecentPolls(int amount) {
        return pollRepo.findAllByOrderByIdDesc(amount);
    }
}
