-- Criação do Banco de Dados
CREATE SCHEMA intellichurras;
-- Criação da Tabela de Usuários
CREATE TABLE `intellichurras`.`users` (
  `user` VARCHAR(255) NOT NULL,
  `pass` VARCHAR(255) NOT NULL,
  `question` VARCHAR(100) NOT NULL,
  `answer` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC) VISIBLE);
-- Criação da Tabela de Carnes
CREATE TABLE `intellichurras`.`meats` (
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `point` VARCHAR(100) NOT NULL,
  `temp_min` DOUBLE NOT NULL,
  `temp_max` DOUBLE NOT NULL,
  `user` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`name`, `user`),
  INDEX `user_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `userMeat`
    FOREIGN KEY (`user`)
    REFERENCES `intellichurras`.`users` (`user`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
-- Criação da Tabela de Espetos
CREATE TABLE `intellichurras`.`sticks` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `user` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`, `user`),
  INDEX `user_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `userStick`
    FOREIGN KEY (`user`)
    REFERENCES `intellichurras`.`users` (`user`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
-- Criação da Tabela de Churrasco
CREATE TABLE `intellichurras`.`barbecues` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade_pessoas` INT NOT NULL,
  `quantidade_carne` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `status` VARCHAR(100) NOT NULL,
  `user` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `userBarbecue_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `userBarbecue`
    FOREIGN KEY (`user`)
    REFERENCES `intellichurras`.`users` (`user`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

  
  
  
-- Define banco de dados
USE intellichurras;
-- Select users
SELECT * FROM users;
-- Select meats
SELECT * FROM meats;
-- Select sticks
SELECT * FROM sticks;
-- Select barbecues
SELECT * FROM barbecues;
