package com.example.student_administration.repositories;

import com.example.student_administration.models.Course;
import com.example.student_administration.models.Student;

import java.util.List;

public interface IStudentRepository {
    // CRUD operations
    public boolean create(Student student);

    public Student read(int id);

    public List<Student> readAll();

    public boolean update(Student student);

    public boolean delete(int id);

    public List<Course> readStudentsCourses(int id);

    public List<Course> readNotOnCourses(int id);

    public void addCourse(int student_id, int course_id);

    public void removeCourse(int student_id, int course_id);
}


