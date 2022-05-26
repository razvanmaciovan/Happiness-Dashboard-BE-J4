package com.example.demo.repo;

import com.example.demo.Models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface RatingRepo extends JpaRepository<Rating, Long> {
    //to do avg for grades per poll


}
