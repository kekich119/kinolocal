package com.kekich.kinolocal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long animeId;

    private String episodeNumber;

    private String videoUrl;
}
