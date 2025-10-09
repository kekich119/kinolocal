package com.kekich.kinolocal.service;


import com.kekich.kinolocal.model.Anime;
import com.kekich.kinolocal.model.Episode;
import com.kekich.kinolocal.repository.AnimeRepository;
import com.kekich.kinolocal.repository.EpisodeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import java.io.*;


@Service
public class AnimeScannerService {

    private static String PATH;

    private final AnimeRepository animeRepository;
    private final EpisodeRepository episodeRepository;
    private final VideoConverter videoConverter;

    public AnimeScannerService(AnimeRepository animeRepository,
                               EpisodeRepository episodeRepository,
                               VideoConverter videoConverter) {
        this.animeRepository = animeRepository;
        this.episodeRepository = episodeRepository;
        this.videoConverter = videoConverter;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void scanFillDb() {
        System.out.println("🔹 Начинаю конвертацию видео...");
        try (BufferedReader br = new BufferedReader(new FileReader("video_path.txt"))) {
            PATH = br.readLine();
        } catch (IOException e) {
            System.err.println("❌ Не удалось прочитать video_path.txt");
            e.printStackTrace();
            return;
        }

        File root = new File(PATH);
        if (!root.exists() || !root.isDirectory()) return;

        File[] animeDirs = root.listFiles();
        if (animeDirs == null) return;

        for (File animeDir : animeDirs) {
            if (!animeDir.isDirectory()) continue;

            String animeTitle = animeDir.getName();

            Anime anime = animeRepository.findByTitle(animeTitle)
                    .orElseGet(() -> {
                        Anime a = new Anime();
                        a.setTitle(animeTitle);
                        return animeRepository.save(a);
                    });

            File[] files = animeDir.listFiles((dir, name) ->
                    name.endsWith(".mp4") || name.endsWith(".mkv"));

            if (files == null) continue;
            Arrays.sort(files, Comparator.comparing(File::getName));

            int epNum = 1;
            for (File epFile : files) {
                String videoPath = epFile.getAbsolutePath();

                if (videoPath.endsWith(".mkv")) {
                    videoPath = videoConverter.convertMkvToMp4(epFile);
                    if (videoPath == null) continue;
                }

                String finalVideoPath = videoPath;
                boolean exists = anime.getEpisodes().stream()
                        .anyMatch(e -> e.getVideoUrl().endsWith(new File(finalVideoPath).getName()));

                if (!exists) {
                    Episode episode = new Episode();
                    episode.setEpisodeNumber(epNum++);
                    episode.setVideoUrl("/" + animeTitle + "/" + new File(videoPath).getName());
                    episode.setAnime(anime);
                    episodeRepository.save(episode);
                }
            }
        }
    }
}