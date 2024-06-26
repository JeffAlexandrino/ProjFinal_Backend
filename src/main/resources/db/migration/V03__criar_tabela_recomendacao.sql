CREATE TABLE recomendacao (
    id SERIAL PRIMARY KEY,
    paciente VARCHAR NOT NULL,
    diagnostico VARCHAR NOT NULL,
    recomendacao VARCHAR NOT NULL
);