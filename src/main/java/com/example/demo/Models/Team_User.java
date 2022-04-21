package com.example.demo.Models;

import java.util.List;
import javax.persistence.*;

@Entity
public class Team_User {

    @Id
    @GeneratedValue
    private long id;
    private long teamId;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

}
