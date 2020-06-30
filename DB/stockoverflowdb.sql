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
  `pair_id` VARCHAR(450) NULL,
  PRIMARY KEY (`symbol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(150) NOT NULL,
  `description` TEXT NULL,
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
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (1, 'admin', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Admin', 'Admin', 'McAdmin', NULL, 'ADMIN', 'https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/125568526/original/cd9c93141521436a112722e8c5c0c7ba0d60a4a2/be-your-telegram-group-admin.jpg', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (2, 'rwasek', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Expert', 'Rich', 'Wasek', NULL, 'VERIFIED', 'https://i.imgur.com/IathyS5.jpg', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (3, 'tflores', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Tabatha', 'Flores', NULL, 'VERIFIED', 'https://media-exp1.licdn.com/dms/image/C4E03AQG5IgfODr1FvQ/profile-displayphoto-shrink_800_800/0?e=1598486400&v=beta&t=ICUS9P6C5on4V3oqv2urtzO6c32NASCwBoPj6lHYkDs', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (4, 'rjlopez', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Expert', 'Jeff', 'Lopez', NULL, 'APPLICANT', 'https://i.imgur.com/26zruY6.png', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (5, 'tpapp', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Toni', 'Papp', NULL, 'STANDARD', 'https://i.imgur.com/f84lFBM.jpg', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (6, 'csanders', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Expert', 'Courtney', 'Sanders', NULL, 'APPLICANT', 'https://i.imgur.com/RstvSN1.png', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (7, 'arios', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Aldo', 'Rios', NULL, 'STANDARD', 'https://i.imgur.com/T3f4Sj4.png', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (8, 'ljopson', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Lauren', 'Jopson', NULL, 'STANDARD', 'https://i.imgur.com/GcCIssl.png?1', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (9, 'rjenkins', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Rachel', 'Jenkins', NULL, 'STANDARD', 'https://i.imgur.com/I0Dr7ts.png', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (10, 'bmiller', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Braiden', 'Miller', NULL, 'STANDARD', 'https://scontent.fapa1-1.fna.fbcdn.net/v/t31.0-8/17972278_10213021584398389_5835261068912409918_o.jpg?_nc_cat=110&_nc_sid=09cbfe&_nc_ohc=g-Q4AcH1n_8AX8ogtyg&_nc_ht=scontent.fapa1-1.fna&oh=a9334c62ca83bb3befc490a230b18e7c&oe=5F1E9BD1', '2020-06-05T09:26', 1);
INSERT INTO `user` (`id`, `username`, `password`, `flair`, `first_name`, `last_name`, `email`, `role`, `profile_picture`, `create_date`, `enabled`) VALUES (11, 'jdebroux', '$2a$10$yCcNV79QkFYwlL9kSqCC4eGSrhkI1/ofG.o8vCh0g5OdwSdV7bwre', 'Beginner', 'Joe', 'DeBroux', NULL, 'STANDARD', 'https://i.imgur.com/pOcfSDk.png', '2020-06-05T09:26', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('AA', 'Arthur Aardvark Inc.', 'NASDAQ', NULL);
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('BB', 'Bullet Bill Ltd.', 'NYSE', NULL);
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('MSFT', 'Microsoft', 'NASDAQ', 'https://ssltvc.forexprostools.com/?pair_ID=252&height=480&width=650&interval=300&plotStyle=candles&domain_ID=1&lang_ID=1&timezone_ID=7\n');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('RTX', 'Raytheon', 'NYSE', '8235');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('FSLY', 'Fastly', 'NYSE', '1130931');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('SPOT', 'Spotify', 'NYSE', '1072316');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('ORCL', 'Oracle', 'NYSE', '274');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('DIS', 'The Walt Disney Co.', 'NYSE', '258');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('FSLR', 'First Solar', 'NASDAQ', '13569');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('BBY', 'Best Buy', 'NYSE', '8077');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('GOOGL', 'Alphabet', 'NASDAQ', '6369');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('PFE', 'Pfizer', 'NYSE', '7989');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('TSLA', 'Tesla', 'NASDAQ', '13994');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('NKLA', 'Nikola', 'NASDAQ', '1162166');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('BA', 'Boeing', 'NYSE', '238');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('AMD', 'Advanced Micro Devices', 'NASDAQ', '8274');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('MDB', 'MongoDB', 'NASDAQ', '1052405');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('MU', 'Micron', 'NASDAQ', '8092');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('AAPL', 'Apple', 'NASDAQ', '6408');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('FB', 'Facebook', 'NASDAQ', '26490');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('CCL', 'Carnival', 'NYSE', '22696');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('UAL', 'United Airlines', 'NASDAQ', '13061');
INSERT INTO `stock` (`symbol`, `company_name`, `exchange`, `pair_id`) VALUES ('NVDA', 'NVIDIA', 'NASDAQ', '6497');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (1, 'Should I buy FakeStock?', 'I dunno they are blowing up but I only have $10...', '2020-06-05T09:26', 1, 1);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (2, 'How do I transfer stocks into my account?', 'I bought some options I have through work but I don\'t know how to see them in my Ameritrade account.', '2020-06-05T09:26', 1, 5);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (3, 'My company matches my 401K contributions so should I get one?', 'Like I said, my company matches our contributions. Is this something I should be doing? I\'m 28 btw.', '2020-06-05T09:26', 1, 3);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (4, 'What stocks are on your radar for next week?', 'I think it\'d be super cool to see what people are looking at on the weekends for the trading week ahead.\n', '2020-06-05T09:26', 1, 9);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (5, 'A question for the more experienced traders', 'I\'m brand new to stock trading, and as such I have a practical question. Let\'s assume I buy a stock at $1 on Thursday and it goes up to $2 by close on Friday. I believe it will eventually hit $10, but I also believe it will dip slightly on Monday. It starts to dip Monday, I sell at $1.95, it hits a low of $1.65, then rebounds. I buy in again at $1.70 and ride the wave back up, a week later its at $6 and climbing.\n\nHere\'s my question: Have I made the correct choice to sell and buy back in, or should I have just left my original purchase ride? The only other factor might be that instead of buying back the 248 stocks I sold, I was able to buy 265 the 2nd time around... I\'m 99% sure what I did was risky, but ultimately a good thing, but I\'m smart enough to know I don\'t know enough higher/theoretical mathematics to know for sure. Give me the answer stock gurus!', '2020-06-05T09:26', 1, 11);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (6, 'Best brokerage for one stock buy?', 'I have no experience trading and i am interested to invest on AMAZON stock.\nI will buy one time and let it rest for few years.\nmy question is what is good brokerage for my on the fee section, maybe some brokers just charge buy and sell fees and other also overnight fees.\n', '2020-06-05T09:26', 1, 7);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (7, 'Where do I start with investments?', 'Everybody knows that investing for the future is important, but not everybody knows how to go about it. Where do I start with investments? How much risk should I take?', '2020-06-05T09:26', 1, 8);
INSERT INTO `post` (`id`, `title`, `description`, `created_at`, `enabled`, `user_id`) VALUES (8, 'Investing in Real Estate vs Stocks - or both', 'My primary problem with real estate is simply the risk. Real estate is probably the best debt to have, but what if your properties don\'t rent? It could quickly stack up depending on the amount of properties and debt you have. I hear of real estate investors that have millions of dollars in debt, but they make more on the return than the interest rate so they are still profiting. But what if 3 or 4 of your houses are vacant, and remain that way for a couple months? That could add up quick, and make it almost impossible to pay for.', '2020-06-05T09:26', 1, 10);

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
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (5, 'The broker your company workd through may also require a form be submitted to them.', '2020-06-05T09:26', 1, 2, 2);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (6, 'It\'s never too early to start saving up for retirement!', '2020-06-05T09:26', 1, 2, 3);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (7, 'Boeing and Norwegian cruise lines look promising to me!', '2020-06-05T09:26', 1, 2, 4);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (8, 'Pay attenion to market forecasting and you should be fine.', '2020-06-05T09:26', 1, 2, 5);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (9, 'Actually no one has commission fees anymore --- they make their money elsewhere.', '2020-06-05T09:26', 1, 2, 6);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (10, 'Invest in tech funds.', '2020-06-05T09:26', 1, 2, 7);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (11, 'I don\'t know if I\'d invest in real estate right now. It\'s hot but it\'s hard to track. Invest in equities until the housing market comes down a bit.', '2020-06-05T09:26', 1, 2, 8);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (12, 'Sorry that wasn\'t helpful --- you\'ll have to pay closer attention to trends and gather info that way.', '2020-06-05T09:26', 1, 2, 1);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (13, 'Likely a W9 btw but definitely don\'t be afraid to reach out to them or even the HR person at your company.', '2020-06-05T09:26', 1, 2, 2);
INSERT INTO `comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `post_id`) VALUES (14, 'Actually there\'s a webinar related to this, you should check it out!', '2020-06-05T09:26', 1, 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `webinar`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (1, 'Day Trading 101', 'Don\'t even know what questions to ask? This is the place for you!', '2020-07-15T08:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2019/02/04/02/42/stock-exchange-3973854_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 30, 1, 1);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (2, 'Getting Started: How to Stock', 'The basics of the basics. Please join us for an open forum discussion where you get your questions answered!', '2020-07-15T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2017/05/16/15/08/courses-2318035_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 100, 1, 2);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (3, 'How to Invest for Retirement Depending on Your Age', 'Did you know different generations should be investing differently? Who should invest in a tech fund? Fnd out this and more in this webinar!', '2020-07-07T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2020/05/31/09/41/analysis-5242025_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 50, 1, 3);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (4, 'Tax Liabilities: Short vs Long Term Gains', 'Find out why you need to be worrying about taxation in relation stocks.', '2020-07-08T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2020/05/18/00/12/iphone-5183932_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 25, 1, 3);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (5, 'Beat the Bell', 'It’s easy to become overwhelmed by the swirl of headlines in the financial world. But stocks often move on those headlines — sometimes a lot — and knowing what to make of the price action will set you on the right path for winning trades.', '2020-07-20T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2018/01/12/16/15/graph-3078540_1280.png', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 100, 1, 2);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (6, 'Should You Be Worried About a Market Crash in 2020?', 'It may be time to consider whether your investments are in trouble.', '2020-07-16T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2020/02/25/07/43/stock-exchange-4878214_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 20, 1, 3);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (7, 'Intimidated by the Stock Market? Try Trading Index Funds.', 'A run-down of where index stocks can benefit and hurt your investments. ', '2020-07-22T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2016/03/21/10/13/coins-1270301_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 45, 1, 2);
INSERT INTO `webinar` (`id`, `title`, `description`, `date_time`, `created_at`, `update_date`, `picture`, `meeting_link`, `max_attendees`, `enabled`, `creator_user_id`) VALUES (8, 'Immunize Your Franchise Portfolio', 'There\'s no polite way to put this: The coronavirus (COVID-19) crisis is going to cause a lot of franchise owners who aren\'t properly diversified. Learn how to protect yourself here.', '2020-07-25T10:00', '2020-06-05T09:26', NULL, 'https://cdn.pixabay.com/photo/2020/06/21/04/21/stock-exchange-5323147_1280.jpg', 'https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09', 15, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_webinar`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (1, 8);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (2, 8);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (3, 8);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (4, 8);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (5, 8);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 1);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 2);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 3);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 4);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 5);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 6);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 7);
INSERT INTO `user_webinar` (`user_id`, `webinar_id`) VALUES (6, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `stockoverflowdb`;
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'MSFT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'RTX');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'FSLY');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'SPOT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (2, 'ORCL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (3, 'DIS');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (3, 'FSLR');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (3, 'BBY');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (3, 'GOOGL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (4, 'PFE');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (4, 'TSLA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (4, 'NKLA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (4, 'BA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (5, 'AMD');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (5, 'MDB');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (5, 'MU');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (5, 'AAPL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (6, 'FB');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (6, 'CCL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (6, 'UAL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (6, 'NVDA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (7, 'MSFT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (7, 'RTX');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (7, 'FSLY');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (7, 'SPOT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (7, 'ORCL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (8, 'DIS');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (8, 'BA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (8, 'AMD');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (8, 'MDB');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (8, 'MU');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (9, 'AAPL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (9, 'FB');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (9, 'CCL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (9, 'UAL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (10, 'NVDA');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (10, 'MSFT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (10, 'RTX');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (10, 'FSLY');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (11, 'SPOT');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (11, 'ORCL');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (11, 'DIS');
INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (11, 'AAPL');
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
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (4, 5, 5, 'Thank you!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (5, 6, 5, 'Thanks!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (6, 7, 5, 'Great!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (7, 8, 5, 'Woo!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (8, 9, 5, 'Awesome!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (9, 10, 5, 'Yay!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (10, 11, 5, 'Wow!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (11, 12, 5, 'Yippee!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (4, 13, 5, 'Hooray!', '2020-06-05T09:26');
INSERT INTO `comment_rating` (`user_id`, `comment_id`, `rating`, `note`, `created_at`) VALUES (8, 14, 5, 'Will do Rich!', '2020-06-05T09:26');

COMMIT;

