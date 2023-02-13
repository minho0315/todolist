package com.minho.todolist.web;

import com.minho.todolist.domain.Member;
import com.minho.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
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
        model.addAttribute("member", Member.class);
        return "members/addForm";
    }

    @PostMapping("/members/add")
    public String save(Member member) {
        memberService.saveMember(member);
        return "redirect:/home";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("member", Member.class);
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(Member member) {
        List<Member> findMember = memberService.findByIdPassword(member);

        if (findMember.isEmpty()) {
            return "members/login";
        } else {
            return "todos";
        }
    }

    @GetMapping("/members/logout")
    public String logout() {
        return "home";
    }

}
