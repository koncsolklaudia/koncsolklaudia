package com.example.programmerfoxclub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Fox fox;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public User(String username, String password, Fox fox) {
//        this.username = username;
//        this.password = password;
//        this.fox = fox;
//    }
}
