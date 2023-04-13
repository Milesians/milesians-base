CREATE TABLE lemon_authorization
(
    book_code     VARCHAR(255) NOT NULL,
    access_token  VARCHAR(255),
    refresh_token VARCHAR(255),
    create_time   TIMESTAMP WITHOUT TIME ZONE,
    update_time   TIMESTAMP WITHOUT TIME ZONE,
    app_type      VARCHAR(255) NOT NULL,
    id_book_code  VARCHAR(255) NOT NULL,
    id_app_type   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_lemon_authorization PRIMARY KEY (id_book_code, id_app_type)
);