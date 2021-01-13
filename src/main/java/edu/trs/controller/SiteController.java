package edu.trs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        return "contact";
    }

    @GetMapping("/deals")
    public String login(){
        return "deals";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
