package com.kekich.kinolocal.service;

import com.kekich.kinolocal.repository.AnimeRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }




}

