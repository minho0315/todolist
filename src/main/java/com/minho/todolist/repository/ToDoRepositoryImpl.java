package com.minho.todolist.repository;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.domain.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoRepositoryImpl implements ToDoRepository {

    private final EntityManager em;

    public Long save(ToDo toDo) {
        if (toDo.getId() == null) {
            em.persist(toDo);
        } else {
            em.merge(toDo);
        }
        return toDo.getId();
    }

    public ToDo findOne(Long id) {
        return em.find(ToDo.class, id);
    }

    public List<ToDo> findAll() {
        return em.createQuery("select t from ToDo t", ToDo.class).getResultList();
    }

    public void remove(ToDo toDo) {
        em.remove(toDo);
    }
}
