package com.kekich.kinolocal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "episode")
@Data
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "anime_id")
    @JsonBackReference
    private Anime anime;

    private int episodeNumber;

    private String videoUrl;
}