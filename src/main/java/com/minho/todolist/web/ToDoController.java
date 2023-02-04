package com.minho.todolist.web;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/todos")
    public String todolist(Model model)
    {
        List<ToDo> toDos = toDoService.findToDos();
        model.addAttribute("toDos",toDos);
        return "/todos/todolist";
    }

    @PostMapping(value = "/todos/{toDoId}/cancel")
    public String cancelToDo(@PathVariable("toDoId") Long toDoId) {
        toDoService.cancelTodo(toDoId);
        log.info(toDoId.toString());
        return "redirect:/todos";
    }

    @GetMapping("/todos/add")
    public String addForm(Model model)
    {
        ToDo toDo = new ToDo();
        toDo.setState(true);
        model.addAttribute("toDo", toDo);
        return "/todos/addForm";
    }

    @PostMapping("/todos/add")
    public String save(ToDo toDo)
    {
        toDoService.saveToDo(toDo);
        return "redirect:/todos";
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

}
