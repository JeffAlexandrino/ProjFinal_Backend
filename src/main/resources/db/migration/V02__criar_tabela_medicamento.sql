CREATE TABLE medicamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dosagem VARCHAR(255) NOT NULL,
    fabricante VARCHAR(255) NOT NULL,
    qtd_estoque INTEGER NOT NULL,
    data_validade DATE NOT NULL
);