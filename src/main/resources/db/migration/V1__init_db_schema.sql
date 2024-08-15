CREATE TABLE IF NOT EXISTS currency
(
    id bigserial PRIMARY KEY NOT NULL,
    code VARCHAR(4) NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    rates TEXT NOT NULL
);