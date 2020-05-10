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
