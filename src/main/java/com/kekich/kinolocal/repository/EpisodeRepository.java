package com.kekich.kinolocal.repository;

import com.kekich.kinolocal.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    boolean existsByVideoUrl(String videoUrl);

    List<Episode> findByAnimeId(Long animeId);


}
