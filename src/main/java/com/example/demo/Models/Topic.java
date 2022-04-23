package com.example.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Poll.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private List<Poll> polls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
