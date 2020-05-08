package com.example.student_administration.controllers;

import com.example.student_administration.models.Student;
import com.example.student_administration.repositories.IStudentRepository;
import com.example.student_administration.repositories.InMemoryStudentRepositoryImpl;
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
        model2.addAttribute("courses", studentRepository.readCourses(id));
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
}
