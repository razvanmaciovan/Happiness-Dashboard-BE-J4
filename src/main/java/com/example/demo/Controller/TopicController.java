package com.example.demo.Controller;

import com.example.demo.Models.Topic;
import com.example.demo.Services.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/topic")
@CrossOrigin(origins = "http://localhost:4200")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @Operation(
            summary = "Gets an Topic!",
            description = "This function returns an Topic with the given ID from database.\n\n" +
                    "__Usage:__ localhost:8080/api/topic/get/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Topic with given ID was found and returned successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No topic with such ID!")
    })
    @GetMapping(path = "/get/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        Optional<Topic> foundTopic = topicService.getTopic(id);
        return foundTopic
                // if the Optional is not empty we map it to an ResponseEntity and return it
                .map(topic -> new ResponseEntity<>(topic, OK))
                .orElseGet(() -> new ResponseEntity<>(null, NOT_FOUND)); // else we return this one
    }

    @Operation(
            summary = "Gets all Topics!",
            description = "This function returns all Topics from the database.\n\n" +
                    "__Usage:__ localhost:8080/api/topic/get/all"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Topic with given ID was found and returned successfully!")
    })
    @GetMapping(path = "/get/all", produces = APPLICATION_JSON_VALUE)
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @Operation(
            summary = "Adds an Topic!",
            description = "This function adds an Topics inside the database.\n\n" +
                    "__Usage:__ localhost:8080/api/topic/add"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Topic with given ID was found and returned successfully!")
    })
    @PostMapping(path = "/add")
    public ResponseEntity<String> addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
        return new ResponseEntity<>("Topic added successfully!", CREATED);
    }

    @Operation(
            summary = "Deletes an Topic!",
            description = "This function deletes an Topics with given ID from the database.\n\n" +
                    "__Usage:__ localhost:8080/api/topic/delete/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Topic with given ID was found and deleted successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Topic with given ID was not found!")
    })
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
        boolean deletedSuccessfully = topicService.deleteTopic(id);
        if (deletedSuccessfully)
            return new ResponseEntity<>("Topic with ID " + id + " was deleted successfully!", OK);
        return new ResponseEntity<>("Topic with given ID was not found!", NOT_FOUND);
    }

    @Operation(
            summary = "Updates an Topic!",
            description = "This function updates the credentials of an Topics with given ID from the database.\n\n" +
                    "__Usage:__ localhost:8080/api/topic/update/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Topic with given ID was found and updated successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Topic with given ID was not found!")
    })
    @PutMapping(path = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Optional<Topic> foundTopic = topicService.updateTopic(id, topic);
        return foundTopic
                .map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

}
