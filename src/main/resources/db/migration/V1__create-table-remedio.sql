CREATE TABLE remedio (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    via TEXT NOT NULL,
    lote TEXT NOT NULL,
    quantidade TEXT NOT NULL,
    validade TEXT NOT NULL,
    laboratorio TEXT NOT NULL
);