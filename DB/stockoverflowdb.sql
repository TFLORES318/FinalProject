-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema stockoverflowdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `stockoverflowdb` ;

-- -----------------------------------------------------
-- Schema stockoverflowdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stockoverflowdb` DEFAULT CHARACTER SET utf8 ;
USE `stockoverflowdb` ;

-- -----------------------------------------------------
-- Table `portfolio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio` ;

CREATE TABLE IF NOT EXISTS `portfolio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cost_basis` DECIMAL(15,2) NOT NULL,
  `dividend_yield` DECIMAL(5,2) NULL,
  `total_shares` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `flair` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `role` ENUM('STANDARD', 'VERIFIED', 'APPLICANT', 'ADMIN') NOT NULL,
  `profile_picture` VARCHAR(4500) NULL,
  `create_date` DATETIME NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `portfolio_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_portfolio1_idx` (`portfolio_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_portfolio1`
    FOREIGN KEY (`portfolio_id`)
    REFERENCES `portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stock` ;

CREATE TABLE IF NOT EXISTS `stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `symbol` VARCHAR(45) NOT NULL,
  `company_name` VARCHAR(45) NOT NULL,
  `exchange` VARCHAR(45) NOT NULL,
  `portfolio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_stock_portfolio1_idx` (`portfolio_id` ASC),
  CONSTRAINT `fk_stock_portfolio1`
    FOREIGN KEY (`portfolio_id`)
    REFERENCES `portfolio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(150) NOT NULL,
  `description` VARCHAR(300) NULL,
  `create_date` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webinar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webinar` ;

CREATE TABLE IF NOT EXISTS `webinar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `create_date` DATETIME NOT NULL,
  `update_date` DATETIME NULL,
  `picture` VARCHAR(4500) NULL,
  `meeting_link` VARCHAR(4500) NOT NULL,
  `max_attendees` INT NOT NULL,
  `creator_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`creator_user_id` ASC),
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`creator_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user_idx` (`user_id` ASC),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  INDEX `fk_comment_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `webinar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_event` ;

CREATE TABLE IF NOT EXISTS `user_event` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  INDEX `fk_user_event_user1_idx` (`user_id` ASC),
  INDEX `fk_user_event_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_user_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `webinar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS stockuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'stockuser'@'localhost' IDENTIFIED BY 'stockuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'stockuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`, `portfolio_id`) VALUES (1, 'admin', 'admin', 'Admin', 'Admin', 'McAdmin', NULL, 'ADMIN', NULL, NULL, 1, NULL);

COMMIT;

