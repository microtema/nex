CREATE TABLE hibernate_sequence
(
    next_val NUMERIC(19)
);

CREATE TABLE URLEntry
(
    id NUMERIC(19) PRIMARY KEY NOT NULL,
    dateCreated DATETIME,
    dateUpdated DATETIME,
    longUrl TEXT NOT NULL,
    shortUrl VARCHAR(256) NOT NULL,
    version NUMERIC(19)
);