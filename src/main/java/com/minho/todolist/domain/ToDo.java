package com.minho.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ToDo {

    private Long id;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime endData;
    private DoState state;
}
