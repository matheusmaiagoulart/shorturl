CREATE TABLE urls (
    short_url     NVARCHAR(30) PRIMARY KEY NOT NULL,
    owner_id      BIGINT                   NOT NULL,
    original_url  NVARCHAR(2048)           NOT NULL,
    active        BIT                      NOT NULL,
    expire_at     DATETIME2(7)                 NULL,
    created_at    DATETIME2(7)             NOT NULL,

    CONSTRAINT unique_url_owner UNIQUE (owner_id, original_url)
);

CREATE UNIQUE INDEX IX_unique_url_owner ON urls (owner_id, original_url);