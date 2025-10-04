create table episode
(
    id SERIAL PRIMARY KEY,
    animeId INTEGER NOT NULL,
    episodeNumber INTEGER NOT NULL,
    videoUrl TEXT NOT NULL
)