create table episode
(
    id serial primary key,
    episode_number text not null,
    video_url text not null,
    anime_id integer not null references anime(id) on delete cascade
);