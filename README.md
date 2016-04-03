# ProjetoSistCaixaEletronico

|-------------------------------------------------------------|
|-------------------| Banco de dados SQL: |-------------------|
|-------------------------------------------------------------|

CREATE DATABASE sistCaixa;
USE sistCaixa;
CREATE TABLE cliente(
 idCliente int not null auto_increment,
    nome varchar(45) default "Não especificado",
 primary key(idCliente)
);
CREATE TABLE conta(
    idConta int not null auto_increment,
 idCliente int not null,
    estado boolean not null default false,
    saldo double not null default 0,
    PRIMARY KEY(idConta)
);
drop table cliente;
select * from conta;

ALTER TABLE `sistcaixa`.`conta` 
ADD INDEX `idCliente_fk_idx` (`idCliente` ASC)  COMMENT '';


ALTER TABLE `sistcaixa`.`conta` 
ADD CONSTRAINT `idCliente_fk`
  FOREIGN KEY (`idCliente`)
  REFERENCES `sistcaixa`.`cliente` (`idCliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE cliente ADD UNIQUE idCliente (idCliente);
ALTER TABLE conta ADD UNIQUE idConta (idConta);



insert into cliente(idCliente,nome) values(1,"Igor Eduardo");
insert into conta(idConta,idCLiente,estado,saldo) values (1,1,false,500);
insert into cliente(nome) values ("Renan Aragaki");
insert into conta(idCliente,estado,saldo) values(2,false,400);
UPDATE conta SET saldo = (900) WHERE idCliente=1;

