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
        String sqlStatement = "INSERT INTO students (id, first_name, last_name, enrollment_date, cpr) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, convertUtilToSql(student.getEnrollmentDate()));
            statement.setString(5, student.getCpr());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE id=?");
            getSingleStudent.setInt(1, id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setId(rs.getInt("id"));
                studentToReturn.setFirstName(rs.getString("first_name"));
                studentToReturn.setLastName(rs.getString("last_name"));
                studentToReturn.setEnrollmentDate(rs.getDate("enrollment_date"));
                studentToReturn.setCpr(rs.getString("cpr"));
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
                tempStudent.setId(rs.getInt("id"));
                tempStudent.setFirstName(rs.getString("first_name"));
                tempStudent.setLastName(rs.getString("last_name"));
                tempStudent.setEnrollmentDate(rs.getDate("enrollment_date"));
                tempStudent.setCpr(rs.getString("cpr"));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        String sqlStatement = "UPDATE students SET id=?, first_name=?, last_name=?, enrollment_date=?, cpr=? where id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, convertUtilToSql(student.getEnrollmentDate()));
            statement.setString(5, student.getCpr());
            statement.setInt(6, student.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sqlStatement = "DELETE from students where id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
