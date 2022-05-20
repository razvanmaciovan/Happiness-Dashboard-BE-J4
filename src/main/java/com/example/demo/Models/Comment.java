package com.example.demo.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private long pollId;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private Date dateOfCreation;
}
