CREATE TABLE venues (
    id           INT          NOT NULL IDENTITY PRIMARY KEY,
    name         VARCHAR(120) NOT NULL,
    type         VARCHAR(15)  NOT NULL,
    city         VARCHAR(40)  NOT NULL,
    address      TEXT         NOT NULL,
    email        VARCHAR(30),
    phone_number VARCHAR(20),
    website      VARCHAR(120)
);

CREATE TABLE social_medias (
    id       INT          NOT NULL IDENTITY PRIMARY KEY,
    venue_id INT          NOT NULL,
    type     VARCHAR(40)  NOT NULL,
    webpage  VARCHAR(120) NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES venues(id)
);

CREATE TABLE users (
    id           INT           NOT NULL IDENTITY PRIMARY KEY,
    username     VARCHAR(30)   NOT NULL,
    password     VARCHAR(60)   NOT NULL,
    first_name   VARCHAR(30)   NOT NULL,
    last_name    VARCHAR(30)   NOT NULL,
    email        VARCHAR(30)   NOT NULL,
    phone_number VARCHAR(20)   NOT NULL,
    address      TEXT          NOT NULL,
    city         VARCHAR(40)   NOT NULL,
    joined_on    SMALLDATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reviews (
    id           INT           NOT NULL IDENTITY PRIMARY KEY,
    venue_id     INT           NOT NULL,
    user_id      INT           NOT NULL,
    text         TEXT,
    rating       DECIMAL(2, 1) NOT NULL,
    published_on SMALLDATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on   SMALLDATETIME,
    FOREIGN KEY (venue_id) REFERENCES venues(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);