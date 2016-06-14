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

CREATE TABLE horarios (
	id_horario          integer not null primary key,
	descricao           varchar(64) not null unique
);

CREATE TABLE reserva (
	id_reserva			SERIAL NOT NULL PRIMARY KEY,
	id_sala				INTEGER NOT NULL REFERENCES sala(id_sala),
	id_usuario			INTEGER NOT NULL REFERENCES usuario(id_usuario),
	id_horario          integer not null references horarios(id_horario),
	data 				DATE NOT NULL,
	id_motivo			INTEGER NOT NULL REFERENCES motivo(id_motivo)
);

-- horários da manhã
insert into horarios(id_horario, descricao) values (1, '07:30 até 08:20');
insert into horarios(id_horario, descricao) values (2, '08:20 até 09:10');
insert into horarios(id_horario, descricao) values (3, '09:10 até 10:00');
insert into horarios(id_horario, descricao) values (4, '10:20 até 11:10');
insert into horarios(id_horario, descricao) values (5, '11:10 até 12:00');

-- horários da tarde
insert into horarios(id_horario, descricao) values (6, '13:00 até 13:50');
insert into horarios(id_horario, descricao) values (7, '13:50 até 14:40');
insert into horarios(id_horario, descricao) values (8, '14:40 até 15:30');
insert into horarios(id_horario, descricao) values (9, '15:50 até 16:40');
insert into horarios(id_horario, descricao) values (10, '16:40 até 17:30');

-- horários da noite
insert into horarios(id_horario, descricao) values (11, '18:40 até 19:30');
insert into horarios(id_horario, descricao) values (12, '19:30 até 20:20');
insert into horarios(id_horario, descricao) values (13, '20:20 até 21:10');
insert into horarios(id_horario, descricao) values (14, '21:20 até 22:10');
insert into horarios(id_horario, descricao) values (15, '22:10 até 23:00');
