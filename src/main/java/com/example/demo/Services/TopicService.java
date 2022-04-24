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

    public Optional<Topic> getTopic(Long id) {
        return Optional.of(topicRepo.getById(id));
    }

    public List<Topic> getAll() {
        return topicRepo.findAll();
    }

    public void addTopic(Topic topic) {
        topicRepo.save(topic);
    }

    public void deleteTopic(Long id) {
        topicRepo.deleteById(id);
    }

    public void updateTopic(Long id, @NotNull Topic newTopic) {
        Topic updatedTopic = topicRepo.getById(id);
        updatedTopic.setName(newTopic.getName());
        topicRepo.save(updatedTopic);
    }
}
