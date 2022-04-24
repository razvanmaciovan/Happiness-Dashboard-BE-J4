package com.example.demo.Controller;

import com.example.demo.Models.Topic;
import com.example.demo.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/topic")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/get/{id}")
    public Optional<Topic> getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @GetMapping("/get/all")
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @PostMapping("/add")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
    }

    @PostMapping("/update/{id}")
    public void updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
    }

}
