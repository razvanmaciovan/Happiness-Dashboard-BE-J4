package com.example.demo.repo;

import com.example.demo.Models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepo extends JpaRepository<Poll, Long> {

}
