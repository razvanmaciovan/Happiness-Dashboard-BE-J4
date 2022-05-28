package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;
    private long pollId;
    @ApiModelProperty(allowableValues = "id")
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
