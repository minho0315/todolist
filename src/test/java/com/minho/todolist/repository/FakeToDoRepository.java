package com.minho.todolist.repository;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.domain.ToDoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeToDoRepository implements ToDoRepository {

    private Map<Long, ToDo> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Long save(ToDo toDo) {
        store.put(++sequence, toDo);
        toDo.setId(sequence);
        return sequence;
    }

    @Override
    public ToDo findOne(Long id) {
        return store.get(id);
    }

    @Override
    public List<ToDo> findAll() {
        return store.values().stream().collect(Collectors.toList());
    }

    @Override
    public void remove(ToDo toDo) {
        store.remove(toDo.getId());
    }
}
