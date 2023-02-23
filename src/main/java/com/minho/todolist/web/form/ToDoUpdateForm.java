package com.minho.todolist.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ToDoUpdateForm {

    @NotNull
    private Long id;
    @NotBlank
    private String content;
    private Boolean state;
}

