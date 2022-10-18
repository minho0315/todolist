package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoServiceImplTest {

    MemberService memberService = new MemberServiceImpl();
    ToDoService toDoService = new ToDoServiceImpl();

    @Test
    void createTodo()
    {
        //given
        Member member = new Member("test315", "1234", "test");

        //when
        Long savedMemberId = memberService.join(member);
        ToDo todo = toDoService.createToDo(savedMemberId, "go to home");

        //then
        assertThat(savedMemberId).isEqualTo(todo.getMemberId());
    }

}