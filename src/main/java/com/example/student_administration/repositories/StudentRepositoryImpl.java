package com.example.student_administration.repositories;

import com.example.student_administration.models.Student;
import com.example.student_administration.util.DatabaseConnectionManager;

import java.sql.*;
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
            getSingleStudent.setInt(1,id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn.setFirstName(rs.getString("firstName"));
                studentToReturn.setLastName(rs.getString("lastName"));
                studentToReturn.setEnrollmentDate(rs.getDate("enrollment"));
                studentToReturn.setCpr(rs.getString("student_cpr"));
            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<Student> readAll() {
        Student student = null;
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt("student_id"));
                tempStudent.setFirstName(rs.getString("firstName"));
                tempStudent.setLastName(rs.getString("lastName"));
                tempStudent.setEnrollmentDate(rs.getDate("enrollment"));
                tempStudent.setCpr(rs.getString("student_cpr"));
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
