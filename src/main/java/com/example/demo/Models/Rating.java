package com.example.demo.Models;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;
    private long pollId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", pollId=" + pollId +
                ", user=" + user.toString() +
                ", grade=" + grade +
                ", timeStamp=" + timeStamp +
                '}';
    }

}
