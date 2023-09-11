/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  DILUSHA
 * Created: Sep 9, 2023
 */

CREATE SCHEMA `customer_management` ;

CREATE TABLE `customer_management`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(100) NULL,
  `full_name` VARCHAR(255) NULL,
  PRIMARY KEY (`customer_id`));


CREATE TABLE `customer_management`.`addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_line_1` VARCHAR(80) NULL,
  `address_line_2` VARCHAR(80) NULL,
  `address_line_3` VARCHAR(80) NULL,
  `city` VARCHAR(45) NULL,
  `postal_code` VARCHAR(12) NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `customer_management`.`addresses` 
ADD COLUMN `customer_id` INT NOT NULL AFTER `postal_code`,
ADD INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE;
;
ALTER TABLE `customer_management`.`addresses` 
ADD CONSTRAINT `customer_id`
  FOREIGN KEY (`customer_id`)
  REFERENCES `customer_management`.`customers` (`customer_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;



INSERT INTO `customer_management`.`customers` (`short_name`, `full_name`) VALUES ('Dilusha', 'Dilusha Rukshan');
INSERT INTO `customer_management`.`customers` (`short_name`, `full_name`) VALUES ('Mithun', 'Mithun Silva');


INSERT INTO `customer_management`.`addresses` (`address_line_1`, `address_line_2`, `address_line_3`, `city`, `postal_code`, `customer_id`) VALUES ('No 04', 'Main Street', 'Colombo 04', 'Colombo', '11023', '1');
