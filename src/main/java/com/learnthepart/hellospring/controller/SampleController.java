package com.learnthepart.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/sample_one")
    public String sampleMethodOne(Model model) {
        model.addAttribute("words", "the quick brown fox jumps over the lazy dog");
        return "sampleViewOne";
    }

    // The controller is the entry point for web requests
    // such as GET or POST request

    // GET request: User is requesting a resource from our spring boot app
    //            path
    @GetMapping("/hello")
    public String sayHello() {
        // basically you are not returning string in json format
        // you are returning the name of the view, spring boot will look for the view
        // 'some_html_view'
        // inside static folder
        return "some_html_view";
    }

    @GetMapping("/radhi")
    public String showRadhi() {
        return "radhi";
    }
}
