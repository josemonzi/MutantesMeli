CREATE DATABASE IF NOT EXISTS DBMutante;

USE DBMutante;

CREATE TABLE mutante(
    idMutante INT(5) NOT NULL AUTO_INCREMENT,
    dna VARCHAR(200) NOT NULL,
    ctrMutante int(1),
    CONSTRAINT mutante_pk PRIMARY KEY (idMutante)
    );

ALTER TABLE mutante AUTO_INCREMENT = 0;