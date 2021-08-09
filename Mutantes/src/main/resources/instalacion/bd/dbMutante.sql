CREATE DATABASE IF NOT EXISTS DBMutante;

USE DBMutante;

CREATE TABLE mutante(
    id_Mutante INT(50) NOT NULL AUTO_INCREMENT,
    dna VARCHAR(150) NOT NULL,
    ctr_Mutante int(2),
    CONSTRAINT mutante_pk PRIMARY KEY (idMutante)
    );

ALTER TABLE mutante AUTO_INCREMENT = 1;