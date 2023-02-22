package com.minho.todolist.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
