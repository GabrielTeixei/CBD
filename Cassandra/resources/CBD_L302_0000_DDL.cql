
CREATE KEYSPACE IF NOT EXISTS Videos
WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};


CREATE TABLE videos (
    video_id uuid ,
    autor text PRIMARY KEY,
    descricao text,
    nome text,
    selo_temporal timestamp
);


CREATE TABLE video_gestao (
    autor text,
    timestamp_upload timestamp,
    video_id uuid,
    descricao text,
    nome_video text,
    tags set<text>,
    PRIMARY KEY ((autor), timestamp_upload, video_id)
) WITH CLUSTERING ORDER BY (timestamp_upload DESC);


CREATE TABLE comentarios (
    comment_id uuid PRIMARY KEY,
    timestamp timestamp,
    author_username text,
    comment_text text
);


CREATE TABLE video_followers (
    video_id uuid,
    user_id uuid,
    PRIMARY KEY (video_id, user_id)
);


CREATE TABLE video_eventos (
    video_id uuid,
    user_id uuid,
    event_type text,
    event_timestamp timestamp,
    video_time_sec int,
    PRIMARY KEY ((video_id, user_id), event_timestamp)
);

CREATE TABLE video_rating_summary (
    video_id uuid PRIMARY KEY,
    rating_avg double,
    rating_count int
);


CREATE TABLE videos_por_autor (
    autor text,
    video_id uuid,
    nome_video text,
    PRIMARY KEY (autor, video_id)
);

CREATE TABLE comentarios_por_video (
    video_id uuid,
    comment_id text,
    comment_text text,
    timestamp_comment timestamp,
    PRIMARY KEY (video_id, timestamp_comment)
) WITH CLUSTERING ORDER BY (timestamp_comment DESC);






CREATE TABLE comentarios_por_usuario (
    user_id uuid,
    comment_id uuid,
    comment_text text,
    timestamp_comment timestamp,
    PRIMARY KEY (user_id, timestamp_comment)
) WITH CLUSTERING ORDER BY (timestamp_comment DESC);
