package com.minho.todolist.web;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ToDoService toDoService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
