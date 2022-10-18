package com.minho.todolist.service;

import com.minho.todolist.domain.DoState;
import com.minho.todolist.domain.ToDo;


import java.time.LocalDateTime;

public class ToDoServiceImpl implements ToDoService
{
    @Override
    public ToDo createToDo(Long memberId, String content) {
        return new ToDo(memberId, content, LocalDateTime.now(), null, DoState.ToDo);
    }
}
