package com.minho.todolist.service;

import com.minho.todolist.domain.ToDo;

public interface ToDoService {
    ToDo createToDo(Long memberId, String content);
}
