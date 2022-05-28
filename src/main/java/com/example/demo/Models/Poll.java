package com.example.demo.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@SuppressWarnings("unused")
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long topic_id;

    private String title;

    private Boolean status;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateOfCreation;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateOfClosing;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    @ApiModelProperty(hidden = true)
    private List<Comment> comments;

    @OneToMany(targetEntity = Rating.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pollId", referencedColumnName = "id")
    @ApiModelProperty(hidden = true)
    private List<Rating> ratings;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer daysTillClosing;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String topicName;

    public Long getId() {
        return id;
    }

    public Long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Integer getDaysTillClosing() {
        return daysTillClosing;

    }

    public void setDaysTillClosing(Integer daysTillClosing) {
        this.daysTillClosing = daysTillClosing;
    }

    // copies only field that are not null
    public void update(Poll newPoll) {
        this.daysTillClosing = newPoll.daysTillClosing;
        this.status = newPoll.status;
        this.title = newPoll.title;
        this.topic_id = newPoll.topic_id;



    }

//    NOT IMPLEMENTED YET
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public List<Rating> getRatings() {
//        return ratings;
//    }

}
