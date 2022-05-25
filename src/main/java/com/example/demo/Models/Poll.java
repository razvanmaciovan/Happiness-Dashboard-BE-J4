package com.example.demo.Models;


import com.example.demo.Services.TopicService;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = false,hidden = true)
    private Long id;

    public Long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id;
    }

    private Long topic_id;
    private String title;
    private Boolean status;
    @Column(nullable = false)
    @ApiModelProperty(required = false,hidden = true)
    private Date dateOfCreation;
    @ApiModelProperty(required = false,hidden = true)
    private Date dateOfClosing;
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    @ApiModelProperty(required = false,hidden = true)
    private List<Comment> comments;
    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    @ApiModelProperty(required = false,hidden = true)
    private List<Rating> ratings;
    @Transient
    private Integer daysTillClosing;

    @Transient
    private String topicName;

    public Long getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void copyFrom(Poll newPoll) {
        this.topic_id = newPoll.topic_id;
        this.dateOfClosing = newPoll.dateOfClosing;
        this.dateOfCreation = newPoll.dateOfCreation;
        this.status = newPoll.status;
    }

    public String getTopicName() {

        return topicName;
    }

    public void setTopicName(String topicName) {

        this.topicName = topicName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public List<Rating> getRatings() {
//        return ratings;
//    }

    public Integer getDaysTillClosing() {
        return daysTillClosing;
    }

    public void setDaysTillClosing(Integer daysTillClosing) {
        this.daysTillClosing = daysTillClosing;
    }
}
