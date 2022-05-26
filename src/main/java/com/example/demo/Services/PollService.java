package com.example.demo.Services;

import com.example.demo.Models.Poll;
import com.example.demo.Models.Topic;
import com.example.demo.repo.PollRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepo pollRepo;
    private final TopicService topicService;

    @Autowired
    public PollService(PollRepo pollRepo, TopicService topicService) {
        this.pollRepo = pollRepo;
        this.topicService = topicService;
    }

    /* Returns a List of Polls from the DB */
    public List<Poll> getAll() {
        List<Poll> list = pollRepo.findAll();

        for (Poll poll : list)
            updatePollTopicName(poll);

        return list;
    }

    /* Returns an Optional containing the found Poll with the given ID or an Empty one */
    public Optional<Poll> getPoll(Long id) {
        Optional<Poll> poll = pollRepo.findById(id);

        if (poll.isEmpty())
            return Optional.empty();

        updatePollTopicName(poll.get());

        return poll;
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
        originalPoll.update(newPoll);
        pollRepo.updateDateOfClosing(Math.toIntExact(originalPoll.getId()), newPoll.getDaysTillClosing());
    }

    public List<Poll> getMostRecentPolls(int amount) {
        List<Poll> list = pollRepo.findAllByOrderByIdDesc(amount);

        for (Poll poll : list)
            updatePollTopicName(poll);

        return list;
    }

    private void updatePollTopicName(Poll poll) {

        Optional<Topic> topic = topicService.getTopic(poll.getTopic_id());

        if (topic.isEmpty())
            return;

        poll.setTopicName(topic.get().getName());

    }
}
