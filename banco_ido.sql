create database ido;
drop database ido;
use ido;


create table tarefa(
id_tarefa int primary key auto_increment,
titulo varchar(80),
descricao varchar(200),
status bit(1),
data_criacao datetime,
data_inicio datetime,
data_final datetime,
urgencia int,
importancia int
);

create table usuario(
id_usario int primary key auto_increment,
nome varchar(45),
nascimento date,
email varchar(45),
senha varchar(45),
biografia varchar(200),
fk_tarefa int,
foreign Key (fk_tarefa)
references tarefa(id_tarefa)
);

create table etiqueta(
id_etiqueta int primary key auto_increment,
titulo varchar(45),
car varchar(20),
fk_usuario int,
foreign key(fk_usuario)
references tarefa(id_tarefa)
);

create table tarefa_etiqueta(
tarefa_id_tarefa int,
foreign key(tarefa_id_tarefa) references tarefa(id_tarefa),
etiqueta_id_etiqueta int,
foreign key(etiqueta_id_etiqueta) references etiqueta(id_etiqueta)
);

create table sub_tarefa(
id_sub_tarefa int primary key auto_increment,
descricao varchar(200),
status bit(1),
prioridade int,
fk_tarefa int,
foreign key(id_sub_tarefa) references tarefa(id_tarefa)
);

insert into tarefa (titulo, descricao, status, data_criacao, data_inicio, data_final, urgencia, importancia) values
		("Estudar para a prova de historia", "Essa tarefa vou estudar a segunda guerra mundial",
        1, "2022-09-15", "2022-09-23", "2022-09-14", 1, 4);
        
insert into usuario(nome, nascimento, email, senha, biografia, fk_tarefa) values
		("Jose", "2000-09-25", "jose.silva@gmail.com.br", "jose1234", "Esse ");
        
desc tarefa;
desc usuario;
desc etiqueta;
desc tarefa_etiqueta;
desc sub_tarefa;




        