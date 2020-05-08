package com.example.student_administration.repositories;

import com.example.student_administration.models.Course;
import com.example.student_administration.models.Student;

import java.util.List;

public interface ICourseRepository {
    // CRUD operations
    public boolean create(Course course);

    public Course read(int id);

    public List<Course> readAll();

    public boolean update(Course course);

    public boolean delete(int id);

    public List<Student> readStudents(int id);
}
