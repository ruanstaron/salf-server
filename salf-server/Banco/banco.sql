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

--Views
CREATE VIEW v_reserva AS
	SELECT
		r.id_reserva, s.descricao AS sala, h.descricao AS horario, TO_CHAR(r.data, 'DD-MM-YYYY') AS data, m.descricao AS motivo
	FROM
		reserva r JOIN sala s ON r.id_sala = s.id_sala
		JOIN horarios h ON r.id_horario = h.id_horario
		JOIN motivo m ON r.id_motivo = m.id_motivo
	ORDER BY id_reserva