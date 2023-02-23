package com.minho.todolist.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ToDoSaveForm {

    @NotBlank
    private String content;
    private Boolean state;
}
