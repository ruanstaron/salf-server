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

INSERT INTO public.reserva(id_sala, id_usuario, id_horario, data, id_motivo) 
VALUES ((select min(s.id_sala) from sala s), (select min(u.id_usuario) from usuario u), 
3, '2016-05-14', (select min(m.id_motivo) from motivo m));
INSERT INTO public.reserva(id_sala, id_usuario, id_horario, data, id_motivo) 
VALUES ((select max(s.id_sala) from sala s), (select max(u.id_usuario) from usuario u), 
5, '2016-05-15', (select max(m.id_motivo) from motivo m));
