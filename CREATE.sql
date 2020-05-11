DROP SCHEMA IF EXISTS `student_administration` ;
CREATE SCHEMA IF NOT EXISTS `student_administration`;
USE `student_administration` ;

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

CREATE TABLE IF NOT EXISTS `student_administration`.`courses` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(20) NOT NULL,
  `start_date` DATE NULL,
  `ECTS` INT NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC) VISIBLE)
ENGINE = InnoDB;

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

USE student_administration;
INSERT INTO students (first_name,last_name,enrollment_date,student_cpr)
    VALUES('Test','Testsen','2020-04-03','0123456789');
INSERT INTO students (first_name,last_name,enrollment_date,student_cpr)
    VALUES('Test','Testsen','2020-04-03','0123456700');
INSERT INTO courses (course_id,course_name,start_date,ECTS)
    VALUES(10,'Teknik 1','2020-08-20',15);
INSERT INTO courses (course_id,course_name,start_date,ECTS)
    VALUES(20,'Teknik 2','2021-08-20',5);
INSERT INTO Link (student_id,course_id) VALUES (1,20);
SELECT s.first_name, s.enrollment_date,c.course_name,c.start_date
FROM students AS s
         LEFT JOIN link AS l
                   ON s.student_id = l.student_id
         LEFT JOIN courses AS c
                   ON l.course_id = c.course_id
WHERE c.course_name IS NOT NULL;