package com.example.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(targetEntity = Team_User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId" , referencedColumnName = "id")
    private List<User> users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
