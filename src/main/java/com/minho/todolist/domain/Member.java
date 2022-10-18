package com.minho.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String name;

    public Member(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }
}
