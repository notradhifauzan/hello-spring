package com.learnthepart.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnthepart.hellospring.beans.Grade;
import com.learnthepart.hellospring.service.GradeService;

import jakarta.validation.Valid;

@Controller
public class GradeController {
    /* presenting the view and manage the models */

    @Autowired
    GradeService gradeService;

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }

    @GetMapping("/")
    // return a form
    public String gradeForm(Model model,@RequestParam(required = false) String id){
        model.addAttribute("grade", gradeService.getGradeById(id));
        System.out.println("gradeForm: " + model.toString());
        return "forms";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(@Valid Grade grade, BindingResult result){
        System.out.println("has errors?: " + result.hasErrors());
        if(result.hasErrors()){
            return "forms";
        }
        gradeService.submitGrade(grade);
        System.out.println("submitGrade: " + grade.toString());

        return "redirect:/grades";
    }

}

/* NOTES 
 * 
 * what is a spring container?
 * what is the relationship between spring container -> bean -> autowired
 * 
 * instead of creating the object directly inside the class, we're injecting it directly from the spring container
 * :: we're injecting 'GradeService' from the spring container, into 'GradeController'
 * 
*/
