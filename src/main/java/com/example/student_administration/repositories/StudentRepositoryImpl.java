package com.example.student_administration.repositories;

import com.example.student_administration.models.Student;
import com.example.student_administration.util.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements IStudentRepository {
    private Connection conn;

    public StudentRepositoryImpl(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    @Override
    public boolean create(Student student) {
        try{
            PreparedStatement newStudent = conn.prepareStatement("INSERT INTO students (firstName,lastName,enrollment,student_cpr) VALUES (?,?,?,?)");
            newStudent.setString(1,student.getFirstName());
            newStudent.setString(2,student.getLastName());
            newStudent.setDate(3,student.getEnrollmentDate());
            newStudent.setString(4,student.getCpr());

            int rowsInserted = newStudent.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE student_id=?");
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setId(rs.getInt(1));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(rs.getDate(4));
                studentToReturn.setCpr(rs.getString(5));
            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt(1));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(rs.getDate(4));
                tempStudent.setCpr(rs.getString(5));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        try{
            PreparedStatement studentToEdit = conn.prepareStatement("UPDATE students SET firstName=?,lastName=?,enrollment=? student_cpr=? WHERE student_id=?");
            studentToEdit.setString(1,student.getFirstName());
            studentToEdit.setString(2,student.getLastName());
            studentToEdit.setDate(3,student.getEnrollmentDate());
            studentToEdit.setString(4,student.getCpr());
            studentToEdit.setInt(5,student.getId());

            int rowsInserted = studentToEdit.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A student was updated successfully!");
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try{
            PreparedStatement deleteStudent = conn.prepareStatement("DELETE FROM students WHERE student_id=?;");
            deleteStudent.setInt(1,id);

            int rowsInserted = deleteStudent.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("The student whit id=" + id + " was deleted successfully!");
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
