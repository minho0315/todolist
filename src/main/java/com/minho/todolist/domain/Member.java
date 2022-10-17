package com.minho.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private List<ToDo> todoList;
}
