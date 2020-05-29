package com.billsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/register")
    public String toRegister() { return "register"; }
}
