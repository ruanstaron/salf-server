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
	--Se trata de um motivo quando a sala está indisponível
	incidencia			VARCHAR (255)
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

--Inserção de dados
INSERT INTO public.departamento(descricao) VALUES ('Informática');
INSERT INTO public.departamento(descricao) VALUES ('Engenharia elétrica');
INSERT INTO public.departamento(descricao) VALUES ('Engenharia mecânica');
INSERT INTO public.usuario(nome, senha, id_departamento, email, tipo) VALUES ('admin', 'admin', 1, 'administracao@email.com', true);
INSERT INTO public.usuario(nome, senha, id_departamento, email, tipo) VALUES ('user', 'user', 1, 'usuario@email.com', false);
INSERT INTO public.motivo(descricao) VALUES ('Aula');
INSERT INTO public.motivo(descricao, incidencia) VALUES ('Indisponível', 'Computador queimado');
INSERT INTO public.motivo(descricao) VALUES ('Prova');
INSERT INTO public.sala(descricao) VALUES ('Lab1');
INSERT INTO public.sala(descricao) VALUES ('Lab2');
INSERT INTO public.sala(descricao) VALUES ('Lab3');
INSERT INTO public.reserva(id_sala, id_usuario, data, hora, id_motivo) VALUES (1, 1, '2016-05-12', '17:00', 1);
INSERT INTO public.reserva(id_sala, id_usuario, data, hora, id_motivo) VALUES (2, 2, '2016-05-13', '19:00', 2);