package com.example.demo.Services;

import com.example.demo.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    /* Function returns True if a User never rated on a Poll before, false otherwise */
    public boolean checkIfUserRated(long pollId, long userId) {
        Optional<Long> optionalLong = ratingRepo.checkIfUserRated(pollId, userId);
        return optionalLong.isPresent();
    }

    /* Function returns true if a Rating with the given ID exists and deletes it.
            It returns false otherwise. */
    public boolean deleteRatingById(long id) {
        boolean ratingExists = ratingRepo.existsById(id);
        if (ratingExists)
            ratingRepo.deleteById(id);
        return ratingExists;
    }

    /* Function returns the rating of a poll */
    public double getAvgRating(long id) {
        boolean ratingExists = ratingRepo.existsById(id);
        // Rating will be the avg of poll's grades if there is one with the given ID
        if (ratingExists)
            return ratingRepo.getPollRating(id);
        // if there is no poll with such ID -1 will be returned
        return -1;
    }

}
