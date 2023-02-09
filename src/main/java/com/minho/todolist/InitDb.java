package com.minho.todolist;

import com.minho.todolist.domain.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            ToDo toDo1 = createToDo("test1", false);
            em.persist(toDo1);

            ToDo toDo2 = createToDo("test2", false);
            em.persist(toDo2);

            ToDo toDo3 = createToDo("test3", false);
            em.persist(toDo3);

        }

        private ToDo createToDo(String content, boolean state) {
            ToDo toDo = new ToDo();
            toDo.setContent(content);
            toDo.setState(state);
            return toDo;
        }
    }
}
