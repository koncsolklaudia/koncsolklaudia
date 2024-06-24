package com.example.urlaliasertrial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String alias;

    private int hitCount;

    @Column(nullable = false)
    private String secretCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Link link;
}
