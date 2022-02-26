-- MySQL Script generated by MySQL Workbench
-- Sat Feb 26 17:46:05 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestion-formation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestion-formation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestion-formation` DEFAULT CHARACTER SET utf8 ;
USE `gestion-formation` ;

-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_domain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_domain` (
  `id_domain` INT NOT NULL AUTO_INCREMENT,
  `denominate` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_domain`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_course` (
  `id_course` INT NOT NULL AUTO_INCREMENT,
  `id_domaine` INT NOT NULL,
  `created_by` INT NULL,
  PRIMARY KEY (`id_course`),
  INDEX `fk_gf_course_gf_domain1_idx` (`id_domaine` ASC) VISIBLE,
  INDEX `fk_gf_course_gf_user1_idx` (`created_by` ASC) VISIBLE,
  CONSTRAINT `fk_gf_course_gf_domain1`
    FOREIGN KEY (`id_domaine`)
    REFERENCES `gestion-formation`.`gf_domain` (`id_domain`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_course_gf_user1`
    FOREIGN KEY (`created_by`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` TEXT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `id_course` INT NULL,
  PRIMARY KEY (`id_user`),
  INDEX `fk_gf_user_gf_course1_idx` (`id_course` ASC) VISIBLE,
  CONSTRAINT `fk_gf_user_gf_course1`
    FOREIGN KEY (`id_course`)
    REFERENCES `gestion-formation`.`gf_course` (`id_course`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_graph` (
  `id_graph` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_graph`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_training` (
  `id_training` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id_training`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`course_has_training`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`course_has_training` (
  `id_training` INT NOT NULL,
  `id_course` INT NOT NULL,
  PRIMARY KEY (`id_training`, `id_course`),
  INDEX `fk_gf_training_has_gf_course_gf_course1_idx` (`id_course` ASC) VISIBLE,
  INDEX `fk_gf_training_has_gf_course_gf_training_idx` (`id_training` ASC) VISIBLE,
  CONSTRAINT `fk_gf_training_has_gf_course_gf_training`
    FOREIGN KEY (`id_training`)
    REFERENCES `gestion-formation`.`gf_training` (`id_training`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_training_has_gf_course_gf_course1`
    FOREIGN KEY (`id_course`)
    REFERENCES `gestion-formation`.`gf_course` (`id_course`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`learner_has_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`learner_has_graph` (
  `id_learner` INT NOT NULL,
  `id_graph` INT NOT NULL,
  PRIMARY KEY (`id_learner`, `id_graph`),
  INDEX `fk_gf_user_has_gf_graph_gf_graph1_idx` (`id_graph` ASC) VISIBLE,
  INDEX `fk_gf_user_has_gf_graph_gf_user1_idx` (`id_learner` ASC) VISIBLE,
  CONSTRAINT `fk_gf_user_has_gf_graph_gf_user1`
    FOREIGN KEY (`id_learner`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_user_has_gf_graph_gf_graph1`
    FOREIGN KEY (`id_graph`)
    REFERENCES `gestion-formation`.`gf_graph` (`id_graph`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_graph_has_gf_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_graph_has_gf_graph` (
  `id_graph` INT NOT NULL,
  `id_graph_predecessor` INT NOT NULL,
  PRIMARY KEY (`id_graph`, `id_graph_predecessor`),
  INDEX `fk_gf_graph_has_gf_graph_gf_graph2_idx` (`id_graph_predecessor` ASC) VISIBLE,
  INDEX `fk_gf_graph_has_gf_graph_gf_graph1_idx` (`id_graph` ASC) VISIBLE,
  CONSTRAINT `fk_gf_graph_has_gf_graph_gf_graph1`
    FOREIGN KEY (`id_graph`)
    REFERENCES `gestion-formation`.`gf_graph` (`id_graph`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_graph_has_gf_graph_gf_graph2`
    FOREIGN KEY (`id_graph_predecessor`)
    REFERENCES `gestion-formation`.`gf_graph` (`id_graph`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`trainer_edit_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`trainer_edit_graph` (
  `id_graph` INT NOT NULL,
  `id_trainer` INT NOT NULL,
  PRIMARY KEY (`id_graph`, `id_trainer`),
  INDEX `fk_gf_graph_has_gf_user_gf_user1_idx` (`id_trainer` ASC) VISIBLE,
  INDEX `fk_gf_graph_has_gf_user_gf_graph1_idx` (`id_graph` ASC) VISIBLE,
  CONSTRAINT `fk_gf_graph_has_gf_user_gf_graph1`
    FOREIGN KEY (`id_graph`)
    REFERENCES `gestion-formation`.`gf_graph` (`id_graph`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_graph_has_gf_user_gf_user1`
    FOREIGN KEY (`id_trainer`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_skill` (
  `id_skill` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `weight` VARCHAR(45) NOT NULL,
  `id_trainer` INT NOT NULL,
  PRIMARY KEY (`id_skill`),
  INDEX `fk_gf_skill_gf_user1_idx` (`id_trainer` ASC) VISIBLE,
  CONSTRAINT `fk_gf_skill_gf_user1`
    FOREIGN KEY (`id_trainer`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`graph_has_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`graph_has_skill` (
  `id_graph` INT NOT NULL,
  `id_skill` INT NOT NULL,
  PRIMARY KEY (`id_graph`, `id_skill`),
  INDEX `fk_gf_graph_has_gf_skill_gf_skill1_idx` (`id_skill` ASC) VISIBLE,
  INDEX `fk_gf_graph_has_gf_skill_gf_graph1_idx` (`id_graph` ASC) VISIBLE,
  CONSTRAINT `fk_gf_graph_has_gf_skill_gf_graph1`
    FOREIGN KEY (`id_graph`)
    REFERENCES `gestion-formation`.`gf_graph` (`id_graph`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_graph_has_gf_skill_gf_skill1`
    FOREIGN KEY (`id_skill`)
    REFERENCES `gestion-formation`.`gf_skill` (`id_skill`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`prerequisite_of_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`prerequisite_of_skill` (
  `id_skill` INT NOT NULL,
  `id_skill_prerequisite` INT NOT NULL,
  PRIMARY KEY (`id_skill`, `id_skill_prerequisite`),
  INDEX `fk_gf_skill_has_gf_skill_gf_skill2_idx` (`id_skill_prerequisite` ASC) VISIBLE,
  INDEX `fk_gf_skill_has_gf_skill_gf_skill1_idx` (`id_skill` ASC) VISIBLE,
  CONSTRAINT `fk_gf_skill_has_gf_skill_gf_skill1`
    FOREIGN KEY (`id_skill`)
    REFERENCES `gestion-formation`.`gf_skill` (`id_skill`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_skill_has_gf_skill_gf_skill2`
    FOREIGN KEY (`id_skill_prerequisite`)
    REFERENCES `gestion-formation`.`gf_skill` (`id_skill`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_question` (
  `id_question` INT NOT NULL AUTO_INCREMENT,
  `level` VARCHAR(45) NOT NULL,
  `difficulty` VARCHAR(45) NOT NULL,
  `contents` TEXT NOT NULL,
  `id_domain` INT NOT NULL,
  `id_skill` INT NOT NULL,
  PRIMARY KEY (`id_question`),
  INDEX `fk_gf_question_gf_domain1_idx` (`id_domain` ASC) VISIBLE,
  INDEX `fk_gf_question_gf_skill1_idx` (`id_skill` ASC) VISIBLE,
  CONSTRAINT `fk_gf_question_gf_domain1`
    FOREIGN KEY (`id_domain`)
    REFERENCES `gestion-formation`.`gf_domain` (`id_domain`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_question_gf_skill1`
    FOREIGN KEY (`id_skill`)
    REFERENCES `gestion-formation`.`gf_skill` (`id_skill`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_survey` (
  `id_survey` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `id_trainer` INT NOT NULL,
  PRIMARY KEY (`id_survey`),
  INDEX `fk_gf_survey_gf_user1_idx` (`id_trainer` ASC) VISIBLE,
  CONSTRAINT `fk_gf_survey_gf_user1`
    FOREIGN KEY (`id_trainer`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`gf_proposal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`gf_proposal` (
  `id_proposal` INT NOT NULL AUTO_INCREMENT,
  `denominate` VARCHAR(50) NOT NULL,
  `state` TINYINT NOT NULL,
  `id_question` INT NOT NULL,
  PRIMARY KEY (`id_proposal`),
  INDEX `fk_gf_proposal_gf_question1_idx` (`id_question` ASC) VISIBLE,
  CONSTRAINT `fk_gf_proposal_gf_question1`
    FOREIGN KEY (`id_question`)
    REFERENCES `gestion-formation`.`gf_question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`survey_has_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`survey_has_question` (
  `id_survey` INT NOT NULL,
  `id_question` INT NOT NULL,
  PRIMARY KEY (`id_survey`, `id_question`),
  INDEX `fk_gf_survey_has_gf_question_gf_question1_idx` (`id_question` ASC) VISIBLE,
  INDEX `fk_gf_survey_has_gf_question_gf_survey1_idx` (`id_survey` ASC) VISIBLE,
  CONSTRAINT `fk_gf_survey_has_gf_question_gf_survey1`
    FOREIGN KEY (`id_survey`)
    REFERENCES `gestion-formation`.`gf_survey` (`id_survey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_survey_has_gf_question_gf_question1`
    FOREIGN KEY (`id_question`)
    REFERENCES `gestion-formation`.`gf_question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion-formation`.`former_manage_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion-formation`.`former_manage_question` (
  `id_trainer` INT NOT NULL,
  `id_question` INT NOT NULL,
  PRIMARY KEY (`id_trainer`, `id_question`),
  INDEX `fk_gf_user_has_gf_question_gf_question1_idx` (`id_question` ASC) VISIBLE,
  INDEX `fk_gf_user_has_gf_question_gf_user1_idx` (`id_trainer` ASC) VISIBLE,
  CONSTRAINT `fk_gf_user_has_gf_question_gf_user1`
    FOREIGN KEY (`id_trainer`)
    REFERENCES `gestion-formation`.`gf_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gf_user_has_gf_question_gf_question1`
    FOREIGN KEY (`id_question`)
    REFERENCES `gestion-formation`.`gf_question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
