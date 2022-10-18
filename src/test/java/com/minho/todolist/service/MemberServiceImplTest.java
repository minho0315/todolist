package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join()
    {
        //given
        Member member = new Member("test315", "1234", "test");

        //when
        Long savedMemberId = memberService.join(member);
        Member findMember = memberService.findByMember(savedMemberId);

        //then
        assertThat(member).isEqualTo(findMember);

    }
}