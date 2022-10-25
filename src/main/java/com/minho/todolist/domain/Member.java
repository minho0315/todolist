package com.minho.todolist.domain;

import lombok.Data;

@Data
public class Member {

    private Long id;
    private String userId;
    private String password;
    private String userName;

    public Member(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.userName = name;
    }

    public Member() {

    }
}
