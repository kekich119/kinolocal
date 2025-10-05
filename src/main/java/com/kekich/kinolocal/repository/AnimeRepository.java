package com.kekich.kinolocal.repository;

import com.kekich.kinolocal.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {


    Optional<Anime> findByTitle(String title);

    Anime findAnimeById(Long id);
}
