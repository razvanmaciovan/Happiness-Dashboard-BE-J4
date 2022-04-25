package com.example.demo.Services;

import com.example.demo.Models.Topic;
import com.example.demo.repo.TopicRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepo topicRepo;

    @Autowired
    public TopicService(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }

    /* Returns a Topic from DB */
    public Optional<Topic> getTopic(Long id) {
        // We return an Optional containing the Topic if we find one with the given ID or an Empty one.
        return topicRepo.findById(id);
    }

    /* Returns a List of Topics from the DB */
    public List<Topic> getAll() {
        return topicRepo.findAll();
    }

    /* Adds a Topic inside the DB */
    public void addTopic(Topic topic) {
        topicRepo.save(topic);
    }

    /* Deletes the Topic with the given ID from the DB */
    public boolean deleteTopic(Long id) {
        Optional<Topic> topicById = topicRepo.findById(id); // we search for the Topic with the given ID
        // if we find it
        if (topicById.isPresent()) {
            topicRepo.deleteById(id); // we delete it and we return true
            return true;
        }
        return false; // if there was no Topic found with the given ID we return false
    }

    /* Updates the Topic with the given ID */
    public Optional<Topic> updateTopic(Long id, @NotNull Topic newTopic) {
        Optional<Topic> topicById = topicRepo.findById(id); // we search for the Topic with the given ID
        topicById.ifPresent(foundTopic -> { // if we found one
            foundTopic.setName(newTopic.getName()); // we update its name
            topicRepo.save(foundTopic); // and we save it to the DB
        });
        return topicById; // at the end we return an Optional containing the Topic or an empty one
    }
}
