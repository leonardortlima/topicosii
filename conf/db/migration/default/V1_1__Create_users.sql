CREATE TABLE public.users
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    first_name  VARCHAR(80)           NOT NULL,
    last_name   VARCHAR(80)           NOT NULL,
    username    VARCHAR(80)           NOT NULL,
    email       VARCHAR(80)           NOT NULL,
    password    VARCHAR(80)           NOT NULL,
    street      VARCHAR(80)           NOT NULL,
    number      VARCHAR(80)           NOT NULL,
    lat         VARCHAR(80)           NOT NULL,
    lon         VARCHAR(80)           NOT NULL,

    UNIQUE(username),
    UNIQUE(email)
);
