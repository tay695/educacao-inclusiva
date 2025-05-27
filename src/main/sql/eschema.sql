CREATE DATABASE Capacita;
USE Capacita;

CREATE TABLE Curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    area VARCHAR(100) NOT NULL
);

CREATE TABLE Usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(45) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    bio VARCHAR(200),
    avaliacao VARCHAR(200)
);

CREATE TABLE Postagem (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    conteudo TEXT NOT NULL,
    data_publicacao DATETIME NOT NULL,
    id_curso INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);

CREATE TABLE UsuarioCurso (
    data_inscricao DATE,
    estado ENUM ('ativo', 'inativo', 'cancelado'),
    nota_final DECIMAL (5,2),
    id_usuario INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);

CREATE TABLE Aluno (
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Tutor (
    area_especializacao VARCHAR(100) NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Modulo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100),
    descricao TEXT,
    id_curso INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);

CREATE TABLE Videoaula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    url TEXT NOT NULL,
    id_modulo INT NOT NULL,
    FOREIGN KEY (id_modulo) REFERENCES Modulo(id)
);

