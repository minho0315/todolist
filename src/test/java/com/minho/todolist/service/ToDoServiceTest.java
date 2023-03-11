package com.minho.todolist.service;

import com.minho.todolist.domain.ToDo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ToDoServiceTest {

    @Autowired ToDoService toDoService;

    @Test
    public void saveToDo() {

        //given
        ToDo toDo = createToDo("공부하기", false);

        //when
        Long savedToDoId = toDoService.saveToDo(toDo);

        //then
        assertThat(toDo.getId()).isEqualTo(savedToDoId);
    }

    @Test
    public void findOne() {

        //given
        ToDo toDo = createToDo("findOne", false);
        Long savedToDoId = toDoService.saveToDo(toDo);

        //when
        ToDo findToDo = toDoService.findOne(savedToDoId);

        //then
        assertThat(savedToDoId).isEqualTo(findToDo.getId());
    }

    @Test
    public void findToDos() {

        //given
        ToDo toDo1 = createToDo("test1", false);
        ToDo toDo2 = createToDo("test2", false);
        toDoService.saveToDo(toDo1);
        toDoService.saveToDo(toDo2);

        //when
        List<ToDo> toDos = toDoService.findToDos();

        //then
        assertThat(2).isEqualTo(toDos.size());
    }

    @Test
    public void updateToDo() {

        //given
        ToDo toDo = createToDo("test", false);
        Long savedToDoId = toDoService.saveToDo(toDo);

        //when
        toDoService.updateToDo(toDo.getId(), "test1", true);

        //then
        assertThat("test1").isEqualTo(toDo.getContent());
        assertThat(true).isEqualTo(toDo.getState());
    }

    @Test
    public void cancelToDo() {

        //given
        ToDo toDo = createToDo("test", false);
        Long savedToDoId = toDoService.saveToDo(toDo);

        //when
        toDoService.cancelTodo(savedToDoId);
        List<ToDo> toDos = toDoService.findToDos();

        //then
        assertThat(0).isEqualTo(toDos.size());
    }



    private ToDo createToDo(String content, boolean state) {
        ToDo toDo = new ToDo();
        toDo.setContent(content);
        toDo.setState(state);
        return toDo;
    }
}