package com.kekich.kinolocal.service;

import com.kekich.kinolocal.model.Anime;
import com.kekich.kinolocal.model.Episode;
import com.kekich.kinolocal.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }


    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(long id) {
        return animeRepository.findAnimeById(id);
    }






}

