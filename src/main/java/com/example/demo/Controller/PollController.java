package com.example.demo.Controller;

import com.example.demo.Models.Poll;
import com.example.demo.Services.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/poll")
@CrossOrigin(origins = "http://localhost:4200")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @Operation(
            summary = "Gets an Poll!",
            description = "This function returns an Poll with the given ID from database.\n\n" +
                    "__Usage:__ localhost:8080/api/poll/{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Poll with given ID was found and returned successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No Poll with such ID!")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        Optional<Poll> foundPoll = pollService.getPoll(id);
        return foundPoll
                .map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @Operation(
            summary = "Gets all Polls!",
            description = "This function returns all Polls from database.\n\n" +
                    "__Usage:__ localhost:8080/api/poll"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All polls were found and returned successfully!")
    })
    @GetMapping()
    @ResponseStatus(value = OK)
    public List<Poll> getAllPolls() {
        return pollService.getAll();
    }

    @Operation(
            summary = "Adds an Poll!",
            description = "This function adds an Poll in the database.\n\n" +
                    "__Usage:__ localhost:8080/api/poll"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Poll was added successfully!")
    })
    @PostMapping()

    public ResponseEntity<String> addPoll(@RequestBody Poll poll) {
        pollService.addPoll(poll);
        return new ResponseEntity<>("Poll successfully added!", CREATED);
    }

    @Operation(
            summary = "Deletes an Poll!",
            description = "This function deletes an Poll with the given ID from the database.\n\n" +
                    "__Usage:__ localhost:8080/api/poll/delete/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The Poll was found and deleted successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No Polls were found with the given ID!")
    })
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletePoll(@PathVariable Long id) {
        boolean successfullyDeleted = pollService.deletePoll(id);
        return successfullyDeleted
                ? new ResponseEntity<>("Poll with ID " + id + " was successfully deleted!", OK)
                : new ResponseEntity<>("No Polls were found with the given ID", NOT_FOUND);
    }

    @Operation(
            summary = "Updates an Poll!",
            description = "This function updates an Poll credentials with the given ID from the database.\n\n" +
                    "__Usage:__ localhost:8080/api/poll/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The Poll was found and updated successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No Polls were found with the given ID!")
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody Poll poll) {
        Optional<Poll> updatedPoll = pollService.updatePoll(id, poll);
        return updatedPoll
                .map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @Operation(
            summary = "Gets the most recent N public polls!",
            description = "This function returns a list with the most recent N public polls.\n\n" +
                    "__Usage:__ localhost:8080/api/poll/last/{amount}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list was successfully returned!"),
            @ApiResponse(
                    responseCode = "418",
                    description = "Something went horribly wrong u TEAPOT!")
    })
    @GetMapping(path = "/last/{amount}")
    public ResponseEntity<List<Poll>> getLastPolls(@PathVariable int amount) {
        if (amount <= 0) return new ResponseEntity<>(null, I_AM_A_TEAPOT);
        return new ResponseEntity<>(pollService.getMostRecentPolls(amount), OK);
    }

}
