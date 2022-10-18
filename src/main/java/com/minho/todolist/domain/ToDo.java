package com.minho.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDo {

    private Long memberId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime endData;
    private DoState state;

    public ToDo(Long memberId, String content, LocalDateTime createDate, LocalDateTime endData, DoState state) {
        this.memberId = memberId;
        this.content = content;
        this.createDate = createDate;
        this.endData = endData;
        this.state = state;
    }
}
