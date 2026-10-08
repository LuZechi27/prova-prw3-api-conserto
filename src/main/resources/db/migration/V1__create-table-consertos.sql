create table consertos(
    id bigint not null auto_increment,
    data_entrada char(10) not null,
    data_saida char(10) not null,
    nome_mecanico varchar(100) not null,
    anos_experiencia int,
    marca_veiculo varchar(100) not null,
    modelo_veiculo varchar(100) not null,
    ano_veiculo char(4) not null,

    primary key(id)
);