package com.minho.todolist.web;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;
import com.minho.todolist.service.MemberService;
import com.minho.todolist.service.ToDoService;
import com.minho.todolist.web.form.ToDoSaveForm;
import com.minho.todolist.web.form.ToDoUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/todos/{memberId}")
    public String todo(@PathVariable Long memberId, Model model)
    {
        Member member = memberService.findMember(memberId);
        todoMemberId = memberId;
        model.addAttribute("member",member);
        return "/todos/todolist";
    }

    @PostMapping("/todos")
    public String checkMember(Member member, Model model)
    {
        List<Member> findMembers = memberService.findByIdPassword(member.getUserId(), member.getPassword());
        todoMemberId = findMembers.get(0).getId();
        model.addAttribute("member",findMembers.get(0));
        return "/todos/todolist";
    }

    @PostMapping(value = "/todos/{toDoId}/cancel")
    public String cancelToDo(@PathVariable("toDoId") Long toDoId) {
        toDoService.cancelTodo(toDoId);
        return "redirect:/todos";
    }

    @GetMapping("/todos/add")
    public String addForm(Model model)
    {
        model.addAttribute("toDo", new ToDo());
        return "/todos/addForm";
    }

    @PostMapping("/todos/add")
    public String save(@Validated @ModelAttribute("toDo") ToDoSaveForm form, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "todos/addForm";
        }

        Member member = memberService.findMember(todoMemberId);

        ToDo toDo = new ToDo();
        toDo.setContent(form.getContent());
        toDo.setState(form.getState());
        toDo.setMember(member);
        toDoService.saveToDo(toDo);

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
    public String edit(@Validated @ModelAttribute("toDo") ToDoUpdateForm form, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "todos/editForm";
        }

        toDoService.updateToDo(form.getId(), form.getContent(), form.getState());
        return "redirect:/todos";
    }

}
