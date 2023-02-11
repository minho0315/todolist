package com.minho.todolist.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "todo")
public class ToDo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;
    private String content;
    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
