package com.example.demo.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String topic;
    private boolean status;
    private Date dateOfCreation;
    private Date dateOfClosing;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    private List<Comment> comments;

    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    private List<Rating> ratings;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfClosing() {
        return dateOfClosing;
    }

    public void setDateOfClosing(Date dateOfClosing) {
        this.dateOfClosing = dateOfClosing;
    }

}
