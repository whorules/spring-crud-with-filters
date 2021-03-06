CREATE TABLE car
(
    id                      uuid                        NOT NULL,
    model                   text                        NOT NULL,
    manufacture_date        timestamp without time zone NOT NULL,
    max_speed               integer                     NOT NULL,
    is_automatic            boolean                     NOT NULL,
    color                   text                        NOT NULL,
    weight                  integer                     NOT NULL
);
ALTER TABLE car ADD CONSTRAINT car_pkey PRIMARY KEY (id);