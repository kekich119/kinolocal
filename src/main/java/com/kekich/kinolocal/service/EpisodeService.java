package com.kekich.kinolocal.service;

import com.kekich.kinolocal.model.Episode;
import com.kekich.kinolocal.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }


    public List<Episode> getEpisodesByAnimeId(Long id) {
        return episodeRepository.findByAnimeId(id);
    }

}
