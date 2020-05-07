package com.example.student_administration.controllers;

import com.example.student_administration.models.Course;
import com.example.student_administration.models.Student;
import com.example.student_administration.repositories.CourseRepositoryImpl;
import com.example.student_administration.repositories.ICourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoursesController {

    private ICourseRepository courseRepository;

    public CoursesController() {
        this.courseRepository = new CourseRepositoryImpl();
    }

    @GetMapping("/courses")
    public String courses(Model model){
        model.addAttribute("courses", courseRepository.readAll());
        return "courses/list";
    }
    @GetMapping("/courses/create")
    public String coursesCreate(){
        return "courses/create";
    }

    @PostMapping("/courses/realcreate")
    public String realCreateCourse(@ModelAttribute Course courseFromPost){
        courseRepository.create(courseFromPost);
        return "redirect:/courses";
    }

    @GetMapping("/courses/details")
    public String coursesDetails(@RequestParam int id, Model model){
        model.addAttribute("course", courseRepository.read(id));
        return "courses/details";
    }

    @GetMapping("/courses/delete")
    public String coursesDelete(@RequestParam int id, Model model){
        model.addAttribute("student", courseRepository.read(id));
        return "courses/delete";
    }

    @GetMapping("/courses/update")
    public String studentsUpdate(@RequestParam int id, Model model){
        model.addAttribute("course", courseRepository.read(id));
        return "courses/update";
    }

    @PostMapping("/courses/realupdate")
    public String updateStudent(@ModelAttribute Course courseFromPost){
        courseRepository.update(courseFromPost);
        return "redirect:/courses";
    }

    @GetMapping("/courses/realdelete")
    public String deleteStudent(@RequestParam int id){
        courseRepository.delete(id);
        return "redirect:/courses";
    }
}
