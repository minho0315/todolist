package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;
import com.minho.todolist.repository.FakeMemberRepository;
import com.minho.todolist.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

class MemberServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberRepository = new FakeMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void saveMember() {
        //given
        Member member = createMember("save member test", "saveMember", "savePassword", "saveUserId");

        //when
        Long savedMemberId = memberService.saveMember(member);

        //then
        Assertions.assertThat(member.getId()).isEqualTo(savedMemberId);
    }

    @Test
    public void findMember() {
        //given
        Member member = createMember("find member test", "findMember", "findPassword", "findUserId");
        Long savedMemberId = memberService.saveMember(member);

        //when
        Member findMember = memberService.findMember(savedMemberId);

        //then
        Assertions.assertThat(savedMemberId).isEqualTo(findMember.getId());
    }

    @Test
    public void findAll() {
        //given
        Member member1 = createMember("member test1", "member1", "password1" , "userId1");
        Member member2 = createMember("member test2", "member2", "password2", "userId2");
        memberService.saveMember(member1);
        memberService.saveMember(member2);

        //when
        List<Member> findMembers = memberService.findAll();

        //then
        Assertions.assertThat(2).isEqualTo(findMembers.size());
    }

    @Test
    public void findByIdPassword() {
        //given
        Member member = createMember("member test1", "member1", "password1", "userId1");
        memberService.saveMember(member);

        //when
        List<Member> findMembers = memberService.findByIdPassword(member.getUserId(), member.getPassword());

        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMembers.get(0).getId());
    }

    @Test
    public void findByUserId() {
        //given
        Member member = createMember("member test1", "member1", "password1", "userId1");
        memberService.saveMember(member);

        //when
        List<Member> findMembers = memberService.findByUserId(member.getUserId());

        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMembers.get(0).getId());
    }

    public Member createMember(String content, String username, String password, String userId) {
        ToDo toDo = new ToDo();
        toDo.setContent(content);

        List<ToDo> toDos = new ArrayList<>();
        toDos.add(toDo);

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setUserId(userId);
        member.setToDos(toDos);
        return member;
    }
}