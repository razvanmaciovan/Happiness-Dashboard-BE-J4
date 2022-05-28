package com.example.demo.Controller;

import com.example.demo.Models.Rating;
import com.example.demo.Services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(
            summary = "Checks if User rates the same topic twice!",
            description = "Function returns true if an User never rated on Poll before. False otherwise.\n\n" +
                    "__Usage:__ localhost:8080/api/rating/avg/{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User never rated before on this Poll"),
            @ApiResponse(
                    responseCode = "400",
                    description = "User already rated on this Poll")
    })
    @GetMapping("/{pollId}/{userId}")
    public ResponseEntity<Boolean> checkIfUserRated(@PathVariable long pollId, @PathVariable long userId) {
        return ratingService.checkIfUserRated(pollId, userId)
                ? new ResponseEntity<>(true, OK)
                : new ResponseEntity<>(false, BAD_REQUEST);
    }

    @Operation(
            summary = "Returns the rating of a poll!",
            description = "Function returns grades avg of a poll.\n\n" +
                    "__Usage:__ localhost:8080/api/rating/avg/{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Poll with given ID was found and the rating was successfully returned!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No Poll with such ID!")
    })
    @GetMapping("/avg/{id}")
    public ResponseEntity<Double> getAvgRatingOfPoll(@PathVariable long id) {
        double avgRating = ratingService.getAvgRating(id);
        return avgRating == -1
                ? new ResponseEntity<>(avgRating, NOT_FOUND)
                : new ResponseEntity<>(avgRating, OK);
    }

    @PostMapping()
    public ResponseEntity<Boolean> addRating(@RequestBody Rating rating) {

        System.out.println(rating.toString());

        return new ResponseEntity<>(null, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRatingById(@PathVariable long id) {
        boolean deletedSuccessfully = ratingService.deleteRatingById(id);
        return deletedSuccessfully
                ? new ResponseEntity<>(true, OK)
                : new ResponseEntity<>(false, NOT_FOUND);
    }

}
