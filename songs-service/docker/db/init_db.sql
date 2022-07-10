CREATE DATABASE songs;
grant all privileges on database "songs" to "admin";
CREATE TABLE songs(
id              SERIAL      PRIMARY KEY,
name            VARCHAR(32) NOT NULL,
artist          VARCHAR(32) NOT NULL,
album           VARCHAR(32) NOT NULL,
length          BIGINT      NOT NULL,
resource_id     BIGINT      NOT NULL,
year            VARCHAR(8)         NOT NULL
);
