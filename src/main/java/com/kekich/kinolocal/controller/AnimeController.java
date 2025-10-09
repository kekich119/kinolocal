package com.kekich.kinolocal.controller;

import com.kekich.kinolocal.model.Anime;
import com.kekich.kinolocal.service.AnimeScannerService;
import com.kekich.kinolocal.service.AnimeService;
import com.kekich.kinolocal.service.EpisodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeService animeService;
    private final EpisodeService episodeService;
    private final AnimeScannerService animeScannerService;

    public AnimeController(AnimeService animeService, EpisodeService episodeService, AnimeScannerService animeScannerService) {
        this.animeService = animeService;
        this.episodeService = episodeService;
        this.animeScannerService = animeScannerService;
    }


    @GetMapping
    public List<Anime> getAnime() {
        return animeService.getAllAnimes();
    }

}
