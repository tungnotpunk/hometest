CREATE SCHEMA `anymind` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;


CREATE TABLE `anymind`.`charge_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `price_modifier` DOUBLE NULL,
  `payment_method` VARCHAR(20) NULL,
  `final_price` DOUBLE NULL,
  `final_price_in_string` VARCHAR(14) NULL,
  `points` DOUBLE NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`datetime` ASC));
