package com.minho.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String userId;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ToDo> toDos = new ArrayList<>();



    public static Member createMember(String username, String userId,String password, ToDo... toDos) {
        Member member = new Member();
        member.setUsername(username);
        member.setUserId(userId);
        member.setPassword(password);
        for (ToDo toDo : toDos) {
            member.addToDo(toDo);
        }
        return member;
    }

    private void addToDo(ToDo toDo) {
        toDos.add(toDo);
        toDo.setMember(this);
    }
}
