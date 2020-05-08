package com.example.student_administration.repositories;

import com.example.student_administration.models.Course;
import com.example.student_administration.models.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

public class InMemoryStudentRepositoryImpl implements IStudentRepository{
    private List<Student> inMemoryDatabase;

    public InMemoryStudentRepositoryImpl(){
        this.inMemoryDatabase = new ArrayList<Student>(
                Arrays.asList(
                        new Student(1, "Nicklas","Frederiksen", new java.sql.Date(2020,04,03), "31134115-1231"),
                        new Student(2, "Bent","Karlsen", new java.sql.Date(2020,04,03), "31134115-4112"),
                        new Student(3, "Bob","Alicesen",new java.sql.Date(2020,04,03), "233124f14-5551"),
                        new Student(4, "Pelle","Pirat",new java.sql.Date(2020,04,03), "233124f14-5551"),
                        new Student(5, "Frederic","Dabgård",new java.sql.Date(2020,04,03), "233124f14-5551"),
                        new Student(6, "Cecilie","Forbrandt",new java.sql.Date(2020,04,03), "233124f14-5551"),
                        new Student(7, "Natali","Dinozachorus",new java.sql.Date(2020,04,03), "233124f14-5551")
                )
        );
    }

    @Override
    public boolean create(Student student) {
        inMemoryDatabase.add(student);
        return true;
    }

    @Override
    public Student read(int id) {
        for(Student stu : inMemoryDatabase){
            if(stu.getId() == id){
                return stu;
            }
        }
        return null;
    }

    @Override
    public List<Student> readAll() {
        return inMemoryDatabase;
    }

    @Override
    public boolean update(Student student) {
        Student oldStud = read(student.getId());
        oldStud.setFirstName(student.getFirstName());
        oldStud.setLastName(student.getLastName());
        oldStud.setEnrollmentDate(student.getEnrollmentDate());
        oldStud.setCpr(student.getCpr());
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (inMemoryDatabase.contains(read(id))) {
            inMemoryDatabase.remove(read(id));
            return true;
        } else return false;
    }

    @Override
    public List<Course> readStudentsCourses(int id){
        List<Course> ls = new ArrayList<>();
        return ls;
    }

    @Override
    public List<Course> readNotOnCourses(int id) {
        return null;
    }

    @Override
    public void addCourse(int student_id, int course_id) {

    }
}
