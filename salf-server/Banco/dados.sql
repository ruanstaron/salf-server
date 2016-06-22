--Inserção de dados
INSERT INTO public.departamento(descricao) VALUES ('Informática');
INSERT INTO public.departamento(descricao) VALUES ('Engenharia elétrica');
INSERT INTO public.departamento(descricao) VALUES ('Engenharia mecânica');

INSERT INTO public.usuario(nome, senha, id_departamento, email, tipo)
VALUES ('admin', 'admin', (select min(d.id_departamento) from departamento d), 'administracao@email.com', true);
INSERT INTO public.usuario(nome, senha, id_departamento, email, tipo)
VALUES ('user', 'user', (select max(d.id_departamento) from departamento d), 'usuario@email.com', false);

INSERT INTO public.motivo(descricao, incidencia) VALUES ('Aula', false);
INSERT INTO public.motivo(descricao, incidencia) VALUES ('Indisponível', true);
INSERT INTO public.motivo(descricao, incidencia) VALUES ('Prova', false);

INSERT INTO public.sala(descricao) VALUES ('Lab1');
INSERT INTO public.sala(descricao) VALUES ('Lab2');
INSERT INTO public.sala(descricao) VALUES ('Lab3');

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

INSERT INTO public.reserva(id_sala, id_usuario, id_horario, data, id_motivo) 
VALUES ((select min(s.id_sala) from sala s), (select min(u.id_usuario) from usuario u), 
3, '2016-05-14', (select min(m.id_motivo) from motivo m));
INSERT INTO public.reserva(id_sala, id_usuario, id_horario, data, id_motivo) 
VALUES ((select max(s.id_sala) from sala s), (select max(u.id_usuario) from usuario u), 
5, '2016-05-15', (select max(m.id_motivo) from motivo m));
