package com.minho.todolist.controller;

import com.minho.todolist.domain.Member;
import com.minho.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "/members/addForm";
    }

    @PostMapping("/add")
    public String addMembers(@ModelAttribute Member member)
    {
        memberService.join(member);
        log.info("member={}", member);
        return "home";
    }
}
