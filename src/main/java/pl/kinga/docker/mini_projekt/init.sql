CREATE TABLE resident
(
    id            BIGSERIAL PRIMARY KEY,
    full_name     VARCHAR(200) NOT NULL,
    pesel         VARCHAR(11)  NOT NULL UNIQUE,
    city          VARCHAR(100) NOT NULL,
    birth_year    INT          NOT NULL,
    is_registered BOOLEAN      NOT NULL DEFAULT TRUE
);

INSERT INTO resident (full_name, pesel, city, birth_year)
VALUES ('Anna Nowak', '90010112345', 'Kraków', 1990),
       ('Jan Kowalski', '85050567890', 'Warszawa', 1985),
       ('Maria Wiśniewska', '95121298765', 'Kraków', 1995),
       ('Piotr Zieliński', '78030334567', 'Gdańsk', 1978),
       ('Katarzyna Wójcik', '01082256789', 'Warszawa', 2001),
       ('Tomasz Lewandowski', '88062012345', 'Kraków', 1988);