package com.example.demo.repo;

import com.example.demo.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
