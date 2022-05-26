package com.example.demo.repo;

import com.example.demo.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface TopicRepo extends JpaRepository<Topic, Long> {
}
