package com.example.demo.repo;

import com.example.demo.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface TeamRepo extends JpaRepository<Team, Long> {
}
