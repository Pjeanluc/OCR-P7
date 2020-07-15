-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema demo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema demo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `demo` ;

-- -----------------------------------------------------
-- Table `demo`.`bidlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`bidlist` ;

CREATE TABLE IF NOT EXISTS `demo`.`bidlist` (
  `bid_list_id` TINYINT NOT NULL,
  `account` VARCHAR(30) NULL DEFAULT NULL,
  `ask` DOUBLE NULL DEFAULT NULL,
  `ask_quantity` DOUBLE NULL DEFAULT NULL,
  `benchmark` VARCHAR(125) NULL DEFAULT NULL,
  `bid` DOUBLE NULL DEFAULT NULL,
  `bid_list_date` DATETIME(6) NULL DEFAULT NULL,
  `bid_quantity` DOUBLE NULL DEFAULT NULL,
  `book` VARCHAR(125) NULL DEFAULT NULL,
  `commentary` VARCHAR(125) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_name` VARCHAR(125) NULL DEFAULT NULL,
  `deal_name` VARCHAR(125) NULL DEFAULT NULL,
  `deal_type` VARCHAR(255) NULL DEFAULT NULL,
  `revision_date` DATETIME(6) NULL DEFAULT NULL,
  `revision_name` VARCHAR(125) NULL DEFAULT NULL,
  `security` VARCHAR(125) NULL DEFAULT NULL,
  `side` VARCHAR(125) NULL DEFAULT NULL,
  `source_list_id` VARCHAR(125) NULL DEFAULT NULL,
  `status` VARCHAR(10) NULL DEFAULT NULL,
  `trader` VARCHAR(125) NULL DEFAULT NULL,
  `type` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`bid_list_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`curvepoint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`curvepoint` ;

CREATE TABLE IF NOT EXISTS `demo`.`curvepoint` (
  `id` INT NOT NULL,
  `as_of_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `curve_id` INT NULL DEFAULT NULL,
  `term` DOUBLE NULL DEFAULT NULL,
  `value` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`hibernate_sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`hibernate_sequence` ;

CREATE TABLE IF NOT EXISTS `demo`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`rating` ;

CREATE TABLE IF NOT EXISTS `demo`.`rating` (
  `id` TINYINT NOT NULL,
  `fitch_rating` VARCHAR(125) NULL DEFAULT NULL,
  `moodys_rating` VARCHAR(125) NULL DEFAULT NULL,
  `order_number` TINYINT NULL DEFAULT NULL,
  `sandprating` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`rulename`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`rulename` ;

CREATE TABLE IF NOT EXISTS `demo`.`rulename` (
  `id` INT NOT NULL,
  `description` VARCHAR(125) NULL DEFAULT NULL,
  `json` VARCHAR(125) NULL DEFAULT NULL,
  `name` VARCHAR(125) NULL DEFAULT NULL,
  `sql_part` VARCHAR(255) NULL DEFAULT NULL,
  `sql_str` VARCHAR(255) NULL DEFAULT NULL,
  `template` VARCHAR(512) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`trade` ;

CREATE TABLE IF NOT EXISTS `demo`.`trade` (
  `trade_id` INT NOT NULL,
  `account` VARCHAR(30) NULL DEFAULT NULL,
  `benchmark` VARCHAR(255) NULL DEFAULT NULL,
  `book` VARCHAR(255) NULL DEFAULT NULL,
  `buy_price` DOUBLE NULL DEFAULT NULL,
  `buy_quantity` DOUBLE NULL DEFAULT NULL,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `creation_name` VARCHAR(255) NULL DEFAULT NULL,
  `deal_name` VARCHAR(255) NULL DEFAULT NULL,
  `deal_type` VARCHAR(255) NULL DEFAULT NULL,
  `revision_date` DATETIME(6) NULL DEFAULT NULL,
  `revision_name` VARCHAR(255) NULL DEFAULT NULL,
  `security` VARCHAR(255) NULL DEFAULT NULL,
  `sell_price` DOUBLE NULL DEFAULT NULL,
  `sell_quantity` DOUBLE NULL DEFAULT NULL,
  `side` VARCHAR(255) NULL DEFAULT NULL,
  `source_list_id` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `trade_date` DATETIME(6) NULL DEFAULT NULL,
  `trader` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`trade_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `demo`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `demo`.`users` ;

CREATE TABLE IF NOT EXISTS `demo`.`users` (
  `Id` TINYINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(125) NULL DEFAULT NULL,
  `password` VARCHAR(125) NULL DEFAULT NULL,
  `fullname` VARCHAR(125) NULL DEFAULT NULL,
  `role` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
