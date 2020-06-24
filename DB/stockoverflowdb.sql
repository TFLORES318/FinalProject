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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `flair` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `role` ENUM('STANDARD', 'VERIFIED', 'APPLICANT', 'ADMIN') NOT NULL,
  `profile_picture` VARCHAR(4500) NULL,
  `create_date` DATETIME NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stock` ;

CREATE TABLE IF NOT EXISTS `stock` (
  `symbol` VARCHAR(45) NOT NULL,
  `company_name` VARCHAR(200) NOT NULL,
  `exchange` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`symbol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(150) NOT NULL,
  `description` VARCHAR(300) NULL,
  `created_at` DATETIME NOT NULL,
  `enabled` TINYINT NOT NULL,
  `user_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
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
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user_idx` (`user_id` ASC),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webinar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webinar` ;

CREATE TABLE IF NOT EXISTS `webinar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `description` TEXT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL,
  `update_date` DATETIME NULL,
  `picture` VARCHAR(4500) NULL,
  `meeting_link` VARCHAR(4500) NOT NULL,
  `max_attendees` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
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
-- Table `user_webinar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_webinar` ;

CREATE TABLE IF NOT EXISTS `user_webinar` (
  `user_id` INT NOT NULL,
  `webinar_id` INT NOT NULL,
  INDEX `fk_user_event_user1_idx` (`user_id` ASC),
  INDEX `fk_user_webinar_webinar1_idx` (`webinar_id` ASC),
  CONSTRAINT `fk_user_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_webinar_webinar1`
    FOREIGN KEY (`webinar_id`)
    REFERENCES `webinar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_stock` ;

CREATE TABLE IF NOT EXISTS `user_stock` (
  `user_id` INT NOT NULL,
  `stock_symbol` VARCHAR(45) NOT NULL,
  INDEX `fk_user_stock_user1_idx` (`user_id` ASC),
  INDEX `fk_userstock_stock_idx` (`stock_symbol` ASC),
  PRIMARY KEY (`user_id`, `stock_symbol`),
  CONSTRAINT `fk_user_stock_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userstock_stock`
    FOREIGN KEY (`stock_symbol`)
    REFERENCES `stock` (`symbol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webinar_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webinar_rating` ;

CREATE TABLE IF NOT EXISTS `webinar_rating` (
  `webinar_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `rating` INT NOT NULL,
  `rating_note` TEXT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`webinar_id`, `user_id`),
  INDEX `fk_webinar_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_webinar_has_user_webinar1_idx` (`webinar_id` ASC),
  CONSTRAINT `fk_webinar_has_user_webinar1`
    FOREIGN KEY (`webinar_id`)
    REFERENCES `webinar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webinar_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_stock_journal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_stock_journal` ;

CREATE TABLE IF NOT EXISTS `user_stock_journal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `stock_symbol` VARCHAR(45) NOT NULL,
  `content` TEXT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_stockjournal_userstock_idx` (`user_id` ASC, `stock_symbol` ASC),
  CONSTRAINT `fk_stockjournal_userstock`
    FOREIGN KEY (`user_id` , `stock_symbol`)
    REFERENCES `user_stock` (`user_id` , `stock_symbol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource` ;

CREATE TABLE IF NOT EXISTS `resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('VIDEO', 'WEBSITE') NOT NULL,
  `content` TEXT NULL,
  `created_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resource_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_resource_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment_rating` ;

CREATE TABLE IF NOT EXISTS `comment_rating` (
  `user_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `rating` INT NOT NULL,
  `note` TEXT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`, `comment_id`),
  INDEX `fk_comment_rating_comment1_idx` (`comment_id` ASC),
  CONSTRAINT `fk_comment_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_rating_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (1, 'admin', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Admin', 'Admin', 'McAdmin', NULL, 'ADMIN', NULL, '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (2, 'rwasek', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Expert', 'Rich', 'Wasek', NULL, 'VERIFIED', NULL, '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (3, 'tflores', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Tabatha', 'Flores', NULL, 'STANDARD', NULL, '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (4, 'rjlopez', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Expert', 'Jeff', 'Lopez', NULL, 'APPLICANT', NULL, '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (5, 'tpapp', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Toni', 'Papp', NULL, 'STANDARD', NULL, '2020-06-05T09:26', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`) VALUES ('AA', 'Arthur Aardvark Inc.', 'NASDAQ');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`) VALUES ('BB', 'Bullet Bill Ltd.', 'NYSE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (1, 'Should I buy FakeStock?', 'I dunno they are blowing up but I only have $10...', '2020-06-05T09:26', 1, 1);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (2, 'How do I transfer stocks into my account?', 'I bought some options I have through work but I don\'t know how to see them in my Ameritrade account.', '2020-06-05T09:26', 1, 5);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (3, 'My company matches my 401K contributions so should I get one?', 'Like I said, my company matches our contributions. Is this something I should be doing? I\'m 28 btw.', '2020-06-05T09:26', 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (1, 'IDK lol', '2020-06-05T09:26', 1, 2, 1);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (2, 'There\'s a form you need to fill out. Try sending a message through the Message Center!', '2020-06-05T09:26', 1, 3, 2);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (3, 'OMG yes that\'s great that your company has that and you should start saving up for retirment ASAP! Goes for anyone.', '2020-06-05T09:26', 1, 4, 3);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (4, 'I do not love this post!', '2020-06-05T09:26', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `webinar`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (1, 'Day Trading 101', 'Don\'t even know what questions to ask? This is the place for you!', '2020-07-15T08:00', '2020-06-05T09:26', NULL, NULL, 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 30, 1, 1);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (2, 'Getting Started: How to Stock', 'The basics of the basics. Please join us for an open forum discussion where you get your questions answered!', '2020-07-15T10:00', '2020-06-05T09:26', NULL, NULL, 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 100, 1, 2);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (3, 'How to Invest for Retirement Depending on Your Age', 'Did you know different generations should be investing differently? Who should invest in a tech fund? Fnd out this and more in this webinar!', '2020-07-07T10:00', '2020-06-05T09:26', NULL, NULL, 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 50, 1, 3);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (4, 'Tax Liabilities: Short vs Long Term Gains', 'Find out why you need to be worrying about taxation in relation stocks.', '2020-07-08T10:00', '2020-06-05T09:26', NULL, NULL, 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 25, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_webinar`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (1, 'AA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'BB');

COMMIT;


-- -----------------------------------------------------
-- Data for table `webinar_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `webinar_rating` (`webinar_id`, `user_id`, `rating`, `rating_note`, `created_at`) VALUES (1, 1, 5, 'Very cool Rich thank you', '2020-06-05T09:26');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_stock_journal`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user_stock_journal` (`id`, `user_id`, `stock_symbol`, `content`, `created_at`) VALUES (1, 1, 'AA', 'I like this stock and this company seems solid.', '2020-06-05T09:26');
INSERT INTO `user_stock_journal` (`id`, `user_id`, `stock_symbol`, `content`, `created_at`) VALUES (2, 2, 'BB', 'Seems promising - could explode at any moment!', '2020-06-05T09:26');

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `resource` (`id`, `type`, `content`, `created_at`, `user_id`) VALUES (1, 'WEBSITE', 'https://www.marketbeat.com/', '2020-06-05T09:26', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (1, 1, 2, 'Not very helpful Rich', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (5, 2, 5, 'Thank you - that helped me out!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (3, 3, 4, 'Got it, thanks!', '2020-06-05T09:26');

COMMIT;

