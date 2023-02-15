package com.minho.todolist.web;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;
import com.minho.todolist.service.MemberService;
import com.minho.todolist.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;
    private final MemberService memberService;

    private Long todoMemberId;

    @GetMapping("/todos")
    public String todolist(Model model)
    {
        Member member = memberService.findMember(todoMemberId);
        model.addAttribute("member",member);
        return "/todos/todolist";
    }

    @PostMapping("/todos")
    public String checkMember(Member member, Model model)
    {
        List<Member> findMembers = memberService.findByIdPassword(member);
        todoMemberId = findMembers.get(0).getId();
        model.addAttribute("member",findMembers.get(0));
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
        model.addAttribute("toDo", new ToDo());
        return "/todos/addForm";
    }

    @PostMapping("/todos/add")
    public String save(ToDo toDo)
    {
        Member member = memberService.findMember(todoMemberId);
        member.getToDos().add(toDo);
        return "redirect:/todos";
    }

    @GetMapping("/todos/{toDoId}/edit")
    public String editForm(@PathVariable("toDoId") Long toDoId, Model model)
    {
        ToDo findToDo = toDoService.findOne(toDoId);
        model.addAttribute("toDo", findToDo);
        return "/todos/editForm";
    }

    @PostMapping("/todos/{toDoId}/edit")
    public String edit(@ModelAttribute("toDo") ToDo toDo)
    {

        toDoService.updateToDo(toDo.getId(), toDo.getContent(), toDo.getState());
        return "redirect:/todos";
    }

}
