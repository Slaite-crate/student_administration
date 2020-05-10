-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema student_administration
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `student_administration` ;

-- -----------------------------------------------------
-- Schema student_administration
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `student_administration` DEFAULT CHARACTER SET utf8 ;
USE `student_administration` ;

-- -----------------------------------------------------
-- Table `student_administration`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_administration`.`students` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(15) NOT NULL,
  `last_name` VARCHAR(15) NOT NULL,
  `enrollment_date` DATE NOT NULL,
  `student_cpr` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `cpr_UNIQUE` (`student_cpr` ASC) VISIBLE,
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_administration`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_administration`.`courses` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(20) NOT NULL,
  `start_date` DATE NULL,
  `ECTS` INT NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_administration`.`Link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_administration`.`Link` (
  `student_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`student_id`, `course_id`),
  INDEX `course_id_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `student_administration`.`students` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_id`
    FOREIGN KEY (`course_id`)
    REFERENCES `student_administration`.`courses` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
