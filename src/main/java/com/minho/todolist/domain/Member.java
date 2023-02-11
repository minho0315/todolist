package com.minho.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "member")
    private List<ToDo> toDos = new ArrayList<>();
}
