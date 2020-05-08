package com.example.student_administration.controllers;

import com.example.student_administration.models.Student;
import com.example.student_administration.repositories.IStudentRepository;
import com.example.student_administration.repositories.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentsController {

    private IStudentRepository studentRepository;

    public StudentsController(){
        studentRepository = new StudentRepositoryImpl();
    }

    @GetMapping("/students")
    public String studentsList(Model model){
        model.addAttribute("students", studentRepository.readAll());
        return "students/list";
    }

    @GetMapping("/students/create")
    public String studentsCreate(){
        return "students/create";
    }

    @PostMapping("/students/realcreate")
    public String realCreateStudent(@ModelAttribute Student studentFromPost){
        studentRepository.create(studentFromPost);
        return "redirect:/students";
    }

    @GetMapping("/students/details")
    public String studentsDetails(@RequestParam int id, Model model, Model model2){
        model.addAttribute("student", studentRepository.read(id));
        model2.addAttribute("courses", studentRepository.readStudentsCourses(id));
        return "students/details";
    }

    @GetMapping("/students/delete")
    public String studentsDelete(@RequestParam int id, Model model){
        model.addAttribute("student", studentRepository.read(id));
        return "students/delete";
    }

    @GetMapping("/students/update")
    public String studentsUpdate(@RequestParam int id, Model model){
        model.addAttribute("student", studentRepository.read(id));
        return "students/update";
    }

    @PostMapping("/students/realupdate")
    public String updateStudent(@ModelAttribute Student studentFromPost){
        studentRepository.update(studentFromPost);
        return "redirect:/students";
    }

    @GetMapping("/students/realdelete")
    public String deleteStudent(@RequestParam int id){
        studentRepository.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/students/courses")
    public String studentsCourses(@RequestParam int id, Model studentModel, Model onCourseModel, Model notOnModel){
        studentModel.addAttribute("student", studentRepository.read(id));
        onCourseModel.addAttribute("oncourses", studentRepository.readStudentsCourses(id));
        notOnModel.addAttribute("offcourses", studentRepository.readNotOnCourses(id));
        return "students/courses";
    }

    @GetMapping("/students/addCourse")
    public String addCourseToStudent(@RequestParam int student_id, int course_id){
        studentRepository.addCourse(student_id, course_id);
        return "redirect:/students/courses?id="+student_id;
    }

    @GetMapping("/students/removeCourse")
    public String removeCourseFromStudent(@RequestParam int student_id, int course_id){
        studentRepository.removeCourse(student_id, course_id);
        return "redirect:/students/courses?id="+student_id;
    }
}
