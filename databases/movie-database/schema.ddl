use movies;
DROP TABLE IF EXISTS movie_scheduler_task;

CREATE TABLE movie_scheduler_task (
  started_at TIMESTAMP NULL DEFAULT NULL
);

INSERT INTO movie_scheduler_task (started_at) VALUES (NULL);
