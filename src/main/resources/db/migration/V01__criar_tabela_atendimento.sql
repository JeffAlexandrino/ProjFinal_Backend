CREATE TABLE atendimento (
    id SERIAL PRIMARY KEY,
    paciente VARCHAR NOT NULL,
    data DATE NOT NULL,
    medico VARCHAR NOT NULL
);