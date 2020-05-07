package com.example.student_administration.repositories;

import com.example.student_administration.models.Course;
import com.example.student_administration.util.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements ICourseRepository{
    private Connection conn;

    public CourseRepositoryImpl() {
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    @Override
    public boolean create(Course course) {
        String sqlStatement = "INSERT INTO courses (course_name, start_date, ECTS) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setString(1, course.getCourseName());
            statement.setDate(2, course.getStartDate());
            statement.setInt(3, course.getECTS());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public Course read(int id) {
        Course courseToReturn = new Course();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM courses WHERE course_id=?");
            getSingleStudent.setInt(1, id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                courseToReturn = new Course();
                courseToReturn.setId(rs.getInt("course_id"));
                courseToReturn.setCourseName(rs.getString("course_name"));
                courseToReturn.setStartDate(rs.getDate("start_date"));
                courseToReturn.setECTS(rs.getInt("ECTS"));
            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return courseToReturn;
    }

    @Override
    public List<Course> readAll() {
        List<Course> allCourses = new ArrayList<Course>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM courses");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Course tempCourse = new Course();
                tempCourse.setId(rs.getInt("course_id"));
                tempCourse.setCourseName(rs.getString("course_name"));
                tempCourse.setStartDate(rs.getDate("start_date"));
                tempCourse.setECTS(rs.getInt("ECTS"));
                allCourses.add(tempCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    @Override
    public boolean update(Course course) {
        String sqlStatement = "UPDATE courses SET course_id=?, course_name=?, start_date=?, ECTS=? where course_id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, course.getId());
            statement.setString(2, course.getCourseName());
            statement.setDate(3, course.getStartDate());
            statement.setInt(4, course.getECTS());
            statement.setInt(5, course.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sqlStatement = "DELETE from courses where course_id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
