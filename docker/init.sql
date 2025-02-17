-- Enable the pgcrypto extension for UUID generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create CATEGORIA table
CREATE TABLE IF NOT EXISTS CATEGORIA(
                                        ID UUID PRIMARY KEY,
                                        NOME VARCHAR(50) NOT NULL UNIQUE
);

-- Create PRODUTO table
CREATE TABLE IF NOT EXISTS PRODUTO(
                                      ID UUID PRIMARY KEY,
                                      NOME VARCHAR(50) NOT NULL,
                                      PRECO DECIMAL(10,2) NOT NULL CHECK(PRECO>0),
                                      QUANTIDADE INTEGER NOT NULL,
                                      CATEGORIA_ID UUID NOT NULL,
                                      FOREIGN KEY(CATEGORIA_ID) REFERENCES CATEGORIA(ID)
);

-- Insert categories
INSERT INTO CATEGORIA(ID,NOME) VALUES(gen_random_uuid(),'INFORMATICA') ON CONFLICT (NOME) DO NOTHING;
INSERT INTO CATEGORIA(ID,NOME) VALUES(gen_random_uuid(),'ELETRÔNICOS') ON CONFLICT (NOME) DO NOTHING;
INSERT INTO CATEGORIA(ID,NOME) VALUES(gen_random_uuid(),'OUTROS') ON CONFLICT (NOME) DO NOTHING;
INSERT INTO CATEGORIA(ID,NOME) VALUES(gen_random_uuid(),'PAPELARIA') ON CONFLICT (NOME) DO NOTHING;
INSERT INTO CATEGORIA(ID,NOME) VALUES(gen_random_uuid(),'VESTUARIO') ON CONFLICT (NOME) DO NOTHING;
