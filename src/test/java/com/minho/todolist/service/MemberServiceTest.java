package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void 저장() {
        //given

        ToDo toDo = new ToDo();
        toDo.setContent("test1");

        List<ToDo> toDos = new ArrayList<>();
        toDos.add(toDo);

        Member member = new Member();
        member.setUsername("민호");
        member.setPassword("minho");
        member.setToDos(toDos);

        //when
        Long savedMemberId = memberService.saveMember(member);

        //then
        Assertions.assertThat(member.getId()).isEqualTo(savedMemberId);
    }
}