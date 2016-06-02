CREATE TABLE departamento (
	id_departamento		SERIAL NOT NULL PRIMARY KEY,
	descricao			VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE usuario (
	id_usuario			SERIAL NOT NULL PRIMARY KEY,
	nome				VARCHAR(255) NOT NULL UNIQUE,
	senha				VARCHAR(50) NOT NULL,
	id_departamento		INTEGER NOT NULL REFERENCES departamento(id_departamento),
	email 				VARCHAR(255) UNIQUE CHECK (email ~* '^[A-Za-z0-9.%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
	chave				VARCHAR,
	tipo				BOOLEAN
);

CREATE TABLE motivo (
	id_motivo			SERIAL NOT NULL PRIMARY KEY,
	descricao			VARCHAR(255) NOT NULL UNIQUE,
	--Se true, é uma incidência, caso contrário é um motivo
	incidencia			BOOLEAN
);

CREATE TABLE sala (
	id_sala				SERIAL NOT NULL PRIMARY KEY,
	descricao			VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE reserva (
	id_reserva			SERIAL NOT NULL PRIMARY KEY,
	id_sala				INTEGER NOT NULL REFERENCES sala(id_sala),
	id_usuario			INTEGER NOT NULL REFERENCES usuario(id_usuario),
	data 				DATE NOT NULL,
	hora				TIME NOT NULL,
	id_motivo			INTEGER NOT NULL REFERENCES motivo(id_motivo)
);