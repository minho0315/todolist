package com.minho.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoController {

    @PostMapping("/todos")
    public String todolist()
    {
        return "/todos/todolist";
    }

    @GetMapping("/todos/add")
    public String addForm()
    {
        return "/todos/addForm";
    }

    @PostMapping("/todos/add")
    public String save()
    {
        return "/todos/todolist";
    }

    @GetMapping("/todos/edit")
    public String editForm()
    {
        return "/todos/editForm";
    }

    @PostMapping("/todos/edit")
    public String edit()
    {
        return "/todos/editForm";
    }

    @GetMapping("/todos/logout")
    public String logout() {
        return "todos/logout";
    }

}
