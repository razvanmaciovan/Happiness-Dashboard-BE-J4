package com.example.demo.Services;

import com.example.demo.Models.Comment;
import com.example.demo.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public boolean addComment(Comment comment) {

        comment.setDateOfCreation(commentRepo.getDBTimestamp());

        commentRepo.save(comment);
        return true;
    }

    public Optional<Comment> getComment(Long id) {
        return commentRepo.findById(id);
    }

    public List<Comment> getMostRecentComments(int pollId, int amount) {

        return commentRepo.findAllByOrderByIdDesc(pollId, amount);

    }
}
