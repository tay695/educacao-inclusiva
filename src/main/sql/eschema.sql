
USE Capacita;

-- Tabela Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL, 
    salt VARCHAR(255) NOT NULL,
    bio VARCHAR(200),
    avaliacao VARCHAR(200)
);

-- Tabela Aluno (referencia Usuario)
CREATE TABLE Aluno (
    data_nascimento DATE NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

-- Tabela Tutor (referencia Usuario)
CREATE TABLE Tutor (
    area_especializacao VARCHAR(100) NOT NULL,																							
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

-- Tabela Curso
CREATE TABLE Curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    area VARCHAR(100) NOT NULL
);

-- Tabela UsuarioCurso (relacionamento N:N)
CREATE TABLE UsuarioCurso (
    data_inscricao DATE,
    estado ENUM ('ativo', 'inativo', 'cancelado'),
    nota_final DECIMAL(5,2),
    id_usuario INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);


-- Tabela Postagem (referencia Curso)
CREATE TABLE Postagem (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    conteudo TEXT NOT NULL,
    data_publicacao DATETIME NOT NULL,
    id_curso INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES Curso(id)
);

CREATE TABLE Modulo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT
);


CREATE TABLE VideoAula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    url TEXT NOT NULL,
    id_modulo INT NOT NULL,
    FOREIGN KEY (id_modulo) REFERENCES Modulo(id)
);

-- Tabela Comentario (referencia Aluno e Videoaula)
CREATE TABLE Comentario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_Usuario INT,
    id_videoaula INT,
    texto TEXT,
    data_postagem TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_Usuario) REFERENCES Aluno(id_usuario),
    FOREIGN KEY (id_videoaula) REFERENCES Videoaula(id)
);

ALTER TABLE Aluno DROP COLUMN data_nascimento;

ALTER TABLE Usuario ADD COLUMN tipo_usuario VARCHAR(10) NOT NULL;



SELECT * FROM Aluno WHERE id_usuario = 3;
ALTER TABLE Usuario DROP COLUMN salt;
select * from Modulo;

