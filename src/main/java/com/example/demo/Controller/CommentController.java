package com.example.demo.Controller;

import com.example.demo.Models.Comment;
import com.example.demo.Models.Poll;
import com.example.demo.Services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Gets an Comment!",
            description = "This function returns an Comment with the given ID from database.\n\n" +
                    "__Usage:__ localhost:8080/api/comment/{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment with given ID was found and returned successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No Comment with such ID!")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Optional<Comment> foundComment = commentService.getComment(id);
        return foundComment
                .map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }


    @Operation(
            summary = "Adds an Comment!",
            description = "This function adds an Comment in the database.\n\n" +
                    "__Usage:__ localhost:8080/api/comment"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Comment was added successfully!")
    })
    @PostMapping()
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        boolean addedSuccessfully = commentService.addComment(comment);
        return addedSuccessfully
                ? new ResponseEntity<>("Comment successfully added!", CREATED)
                : new ResponseEntity<>("Comment not added!", NOT_ACCEPTABLE);
    }

    @GetMapping(path = "/last/{pollId}/{amount}")
    public ResponseEntity<List<Comment>> getLastComments(@PathVariable int pollId, @PathVariable int amount) {
        if (amount <= 0) return new ResponseEntity<>(null, I_AM_A_TEAPOT);
        return new ResponseEntity<>(commentService.getMostRecentComments(pollId, amount), OK);
    }

}
