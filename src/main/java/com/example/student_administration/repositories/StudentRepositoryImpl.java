package com.example.student_administration.repositories;

import com.example.student_administration.models.Course;
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
        String sqlStatement = "INSERT INTO students (first_name, last_name, enrollment_date, student_cpr) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDate(3, student.getEnrollmentDate());
            statement.setString(4, student.getCpr());
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
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM students WHERE student_id=?");
            getSingleStudent.setInt(1, id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setId(rs.getInt("student_id"));
                studentToReturn.setFirstName(rs.getString("first_name"));
                studentToReturn.setLastName(rs.getString("last_name"));
                studentToReturn.setEnrollmentDate(rs.getDate("enrollment_date"));
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
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt("student_id"));
                tempStudent.setFirstName(rs.getString("first_name"));
                tempStudent.setLastName(rs.getString("last_name"));
                tempStudent.setEnrollmentDate(rs.getDate("enrollment_date"));
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
        String sqlStatement = "UPDATE students SET student_id=?, first_name=?, last_name=?, enrollment_date=?, student_cpr=? where student_id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, student.getEnrollmentDate());
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
        String sqlStatement = "DELETE from students where student_id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Course> readCourses(int id){
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT cs.course_id, cs.course_name, cs.ECTS, cs.start_date FROM courses AS cs LEFT JOIN link AS l ON cs.course_id = l.course_id LEFT JOIN students AS ss ON l.student_id = ss.student_id WHERE ss.student_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Course tempCourse = new Course();
                tempCourse.setId(rs.getInt("course_id"));
                tempCourse.setCourseName(rs.getString("course_name"));
                tempCourse.setECTS(rs.getInt("ECTS"));
                tempCourse.setStartDate(rs.getDate("start_date"));
                courses.add(tempCourse);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courses;
    }
}
