package com.kekich.kinolocal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
}
