package com.kekich.kinolocal.service;

import com.kekich.kinolocal.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }




}
