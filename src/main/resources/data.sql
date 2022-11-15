INSERT INTO usuario (nome, apelido, email, senha, biografia, nascimento, imagem_perfil, imagem_biografia, nivel, autenticado, notificacao) VALUES
	('Julia Veloso Santos', 'juvelososant', 'julia.santos@gmail.com', 'julia123', 'Sou uma universitária e estagiária de medicina, ficando grande parte do tempo fora de casa e não consigo me organizar e gerenciar meu tempo devido a minha rotina puxada','2000-02-11', 'https://i.imgur.com/nr8MEmx.jpg', 'https://i.imgur.com/Y8xZ6yJ.jpg', 6, 0, 0);

INSERT INTO tarefa (titulo, descricao, status, data_inicio, data_final, data_criacao, data_conclusao, urgencia, importancia, fk_usuario) VALUES
	-- JULIA
    ('Trabalho de anatomia', 'Realizar o trabalho de anatomia da faculdade', 1, '2022-09-01', '2022-09-10', '2022-09-09', null, 1, 1, 1),
    ('Estudos', 'Estudar para a prova do semestre', 1, '2022-09-03', '2022-09-05', '2022-09-05', null, 1, 1, 1),
    ('Limpeza', 'Limpar o salão da casa', 0, '2022-09-11', '2022-09-12', '2022-09-12', null, 0, 1, 1),
    ('Trabalho de imunologia', 'Realizar o trabalho de imunologia da faculdade', 1, '2022-09-20', '2022-09-30', '2022-09-28', null, 0, 1, 1),
    ('Buscar irmão', 'Buscar irmão na escola', 0, '2022-09-12', '2022-09-12', '2022-09-12', null, 0, 1, 1),
    ('Comprar roupas', 'Comprar roupas para o final de ano', 0, '2022-09-25', '2022-10-05', '2022-10-01', null, 0, 0, 1),
    ('Cinema', 'Assistir Adão Negro nos cinemas', 0, '2022-10-10', '2022-10-12', '2022-10-11', null, 0, 0, 1),
    ('Atividade de biologia', 'Realizar a atividade de biologia molecular', 0, '2022-10-15', '2022-10-17', '2022-10-17', null, 0, 1, 1),
    ('Presente', 'Comprar presente para minha avó', 0, '2022-10-15', '2022-10-20', '2022-10-18', null, 1, 0, 1),
    ('Compras para a faculdade', 'Comprar luva e estetoscópio', 1, '2022-10-02', '2022-10-05', '2022-10-04', null, 1, 1, 1);

INSERT INTO etiqueta (titulo, cor, fk_usuario) VALUES
	('Faculdade', '5D84C2', 1),
    ('Trabalho', '51BDAB', 1),
    ('Estágio', '7463C7', 1),
    ('Lazer', 'FFCA6D', 1);