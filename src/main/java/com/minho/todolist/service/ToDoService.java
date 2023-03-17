package com.minho.todolist.service;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.domain.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Transactional
    public Long saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public ToDo findOne(Long toDoId) {
        return toDoRepository.findOne(toDoId);
    }

    public List<ToDo> findToDos() {
        return toDoRepository.findAll();
    }

    @Transactional
    public void updateToDo(Long toDoId, String content, Boolean state) {
        ToDo findToDo = toDoRepository.findOne(toDoId);
        findToDo.setContent(content);
        findToDo.setState(state);
    }

    @Transactional
    public void cancelTodo(Long toDoId) {
        ToDo findToDo = toDoRepository.findOne(toDoId);
        toDoRepository.remove(findToDo);
    }
}
