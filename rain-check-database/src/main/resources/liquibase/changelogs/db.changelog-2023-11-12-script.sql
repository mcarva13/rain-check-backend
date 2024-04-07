CREATE SCHEMA IF NOT EXISTS rain_check;

SET SEARCH_PATH = rain_check;

CREATE TABLE IF NOT EXISTS "user"
(
    id                   uuid            NOT NULL,
    user_name            VARCHAR(255)    NOT NULL,
    email                TEXT            NOT NULL,
    CONSTRAINT pk_user   PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS "city"
(
    id                   uuid            NOT NULL,
    city_name            VARCHAR(255)    NOT NULL,
    longitude            numeric(5,2)    NOT NULL,
    latitude             numeric(5,2)    NOT NULL,
    country              VARCHAR(255)    NOT NULL,
    timezone             VARCHAR(255)    NOT NULL,
    CONSTRAINT pk_city   PRIMARY KEY (id),
    UNIQUE (city_name, longitude, latitude)
);
