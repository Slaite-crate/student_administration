package com.example.student_administration.models;

import java.sql.Date;

public class Course {

    private int id;
    private String courseName;
    private Date startDate;
    private int ECTS;

    public Course(int id, String courseName, Date startDate, int ECTS) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.ECTS = ECTS;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", ECTS=" + ECTS +
                '}';
    }
}
