package com.minho.todolist.web;

import com.minho.todolist.domain.Member;
import com.minho.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/add")
    public String signUp(Model model) {
        model.addAttribute("member", new Member());
        return "members/addForm";
    }

    @PostMapping("/members/add")
    public String save(Member member) {
        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(Member member) {
        List<Member> findMember = memberService.findByIdPassword(member);

        if (findMember.isEmpty()) {
            return "redirect:/members/login";
        } else {
            return "todos/todolist";
        }
    }

    @GetMapping("/members/logout")
    public String logout() {
        return "home";
    }

}
