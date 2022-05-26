package com.example.demo.repo;

import com.example.demo.Models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PollRepo extends JpaRepository<Poll, Long> {
    @Query(value = "SELECT * FROM poll WHERE status = 1 ORDER BY id DESC LIMIT :amount", nativeQuery = true)
    List<Poll> findAllByOrderByIdDesc(int amount);

    @Query(value = "SELECT sysdate()", nativeQuery = true)
    Date getDBTimestamp();

    @Query(value = "SELECT sysdate()+INTERVAL :daysTillClose DAY", nativeQuery = true)
    Date getDateOfClosing(@Param("daysTillClose") Integer daysTillClose);

    @Modifying
    @Transactional
    @Query(value =  "UPDATE poll SET date_of_closing=date_of_creation+INTERVAL :daysTillClose DAY WHERE id=:pID ;", nativeQuery = true)
    void updateDateOfClosing(@Param("pID") Integer pID, @Param("daysTillClose") Integer daysTillClose);

}