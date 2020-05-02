package com.example.student_administration.repositories;

import com.example.student_administration.models.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InMemoryStudentRepositoryImpl implements IStudentRepository{
    private List<Student> inMemoryDatabase;

    public InMemoryStudentRepositoryImpl(){
        this.inMemoryDatabase = new ArrayList<Student>(
                Arrays.asList(
                        new Student(1, "Nicklas","Frederiksen", new Date(12312), "31134115-1231"),
                        new Student(2, "Bent","Karlsen", new Date(2141241), "31134115-4112"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "233124f14-5551"),
                        new Student(4, "Pelle","Pirat",new Date(12424141), "233124f14-5551"),
                        new Student(5, "Frederic","Dabgård",new Date(12424141), "233124f14-5551"),
                        new Student(6, "Cecilie","Forbrandt",new Date(12424141), "233124f14-5551"),
                        new Student(7, "Natali","Dinozachorus",new Date(12424141), "233124f14-5551")
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
}
