package com.minho.todolist.service;

import com.minho.todolist.domain.ToDo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ToDoServiceTest {
    @Autowired ToDoService toDoService;

    @Test
    public void 저장() {

        //Given
        ToDo toDo = new ToDo();
        toDo.setState(false);
        toDo.setContent("공부하기");

        //When
        Long savedToDoId = toDoService.saveToDo(toDo);

        //Then
        Assertions.assertThat(toDo.getId()).isEqualTo(savedToDoId);
    }
}