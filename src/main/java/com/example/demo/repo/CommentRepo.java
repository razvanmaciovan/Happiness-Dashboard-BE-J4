package com.example.demo.repo;

import com.example.demo.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT sysdate()", nativeQuery = true)
    Date getDBTimestamp();

    @Query(value = "SELECT * FROM comment WHERE poll_id=:pollId ORDER BY id DESC LIMIT :amount ;", nativeQuery = true)
    List<Comment> findAllByOrderByIdDesc(int pollId, int amount);
}
