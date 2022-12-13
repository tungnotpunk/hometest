CREATE TABLE `anymind`.`charge_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `price_modifier` DOUBLE NULL,
  `payment_method` VARCHAR(20) NULL,
  `final_price` DOUBLE NULL,
  `points` DOUBLE NULL,
  `datetime` DATETIME NULL,
  `datetime_in_hour` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`datetime` ASC, `datetime_in_hour` ASC));
