package com.example.demo.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;
    private long pollId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    @Column
    private int grade;

    private Date timeStamp;

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
