package com.minho.todolist.repository;

import com.minho.todolist.domain.ToDo;

import javax.persistence.EntityManager;
import java.util.List;

public interface ToDoRepository {

    public Long save(ToDo toDo);

    public ToDo findOne(Long id);

    public List<ToDo> findAll();

    public void remove(ToDo toDo);
}
