package com.example.student_administration.controllers;

import com.example.student_administration.models.Student;
import com.example.student_administration.repositories.IStudentRepository;
import com.example.student_administration.repositories.InMemoryStudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentsController {

    private IStudentRepository studentRepository;

    public StudentsController(){
        studentRepository = new InMemoryStudentRepositoryImpl();
    }

    @GetMapping("/students")
    public String studentsList(Model model){
        model.addAttribute("students", studentRepository.readAll());
        return "students/list";
    }

    @GetMapping("/students/details")
    public String studentsDetails(@RequestParam int id, Model model){
        model.addAttribute("info", studentRepository.read(id));
        return "students/details";
    }

    @GetMapping("/students/delete")
    public String studentsDelete(){
        return "students/delete";
    }

    @GetMapping("/students/create")
    public String studentsCreate(){
        return "students/create";
    }

    @GetMapping("/students/update")
    public String studentsUpdate(){
        return "students/update";
    }

}
