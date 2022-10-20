package com.minho.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/members")
    public String addForm()
    {
        return "/members/addForm";
    }

    @PostMapping("/members")
    public String addMembers()
    {
        return "/home";
    }
}
