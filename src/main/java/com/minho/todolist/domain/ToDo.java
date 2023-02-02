package com.minho.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDo {

    private Long id;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime endData;
    private DoState state;

    public ToDo(Long id, String content, LocalDateTime createDate, LocalDateTime endData, DoState state) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.endData = endData;
        this.state = state;
    }
}
