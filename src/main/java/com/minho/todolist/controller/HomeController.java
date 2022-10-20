package com.minho.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "todos/logout";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
