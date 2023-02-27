package com.minho.todolist.web;

import com.minho.todolist.domain.Member;
import com.minho.todolist.service.MemberService;
import com.minho.todolist.web.form.MemberLoginForm;
import com.minho.todolist.web.form.MemberSaveForm;
import com.minho.todolist.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/add")
    public String signUp(Model model) {
        model.addAttribute("member", new Member());
        return "members/addForm";
    }

    @PostMapping("/members/add")
    public String save(@Validated @ModelAttribute("member") MemberSaveForm form, BindingResult bindingResult) {

        List<Member> members = memberService.findByUserId(form.getUserId());
        if (members.size() != 0) {
            bindingResult.rejectValue("userId", "UserIdDuplication",null);
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/addForm";
        }

        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setPassword(form.getPassword());
        member.setUsername(form.getUsername());

        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@Validated @ModelAttribute("member")MemberLoginForm form, BindingResult bindingResult,
                        HttpServletRequest request) {

        List<Member> findMember = memberService.findByIdPassword(form.getUserId(), form.getPassword());
        if (findMember.isEmpty()) {
            bindingResult.reject("UserIdPasswordNotMatch",null,null);
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, findMember.get(0));

        return "redirect:/todos";
    }

    @PostMapping("/members/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
