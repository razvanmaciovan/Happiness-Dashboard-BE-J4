package com.example.demo.repo;

import com.example.demo.Models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@SuppressWarnings("unused")
public interface RatingRepo extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT AVG(grade) FROM rating WHERE poll_id = :id", nativeQuery = true)
    double getPollRating(long id);

    @Query(value = "SELECT user_id FROM rating WHERE user_id = :userId AND poll_id = :pollId", nativeQuery = true)
    Optional<Long> checkIfUserRated(long userId, long pollId);

}
