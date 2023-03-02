package com.minho.todolist.api;

import com.minho.todolist.domain.ToDo;
import com.minho.todolist.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ToDoApiController {

    private final ToDoService toDoService;

    @PostMapping("/api/todos")
    public CreateToDoResponse saveToDo(@RequestBody @Valid CreateToDoRequest request) {

        ToDo toDo = new ToDo();
        toDo.setContent(request.getContent());
        toDo.setState(false);

        Long id = toDoService.saveToDo(toDo);
        return new CreateToDoResponse(id);
    }

    @Data
    static class CreateToDoRequest {

        @NotEmpty
        private String content;
    }

    @Data
    static class CreateToDoResponse {
        private Long id;

        public CreateToDoResponse(Long id) {
            this.id = id;
        }
    }

    @GetMapping("/api/todos")
    public Result getToDo() {
        List<ToDo> findToDos = toDoService.findToDos();

        List<ToDoDto> collect = findToDos.stream()
                .map(t -> new ToDoDto(t.getContent(), t.getState()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ToDoDto {
        private String content;
        private Boolean state;
    }

    @PutMapping("/api/todos/{id}")
    public UpdateToDoResponse updateToDo(@PathVariable("id") Long id, @RequestBody @Valid UpdateToDoRequest request) {

        ToDo findToDo = toDoService.findOne(id);

        if (findToDo == null) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        toDoService.updateToDo(id, request.content, request.state);

        return new UpdateToDoResponse(findToDo.getId(), findToDo.getContent(), findToDo.getState());
    }

    @Data
    static class UpdateToDoRequest {
        private String content;
        private Boolean state;
    }

    @Data
    @AllArgsConstructor
    static class UpdateToDoResponse {
        private Long id;
        private String content;
        private Boolean state;

    }

    @DeleteMapping("/api/todos/{id}")
    public DeleteToDoResponse deleteTodo(@PathVariable("id") Long id) {

        toDoService.cancelTodo(id);
        return new DeleteToDoResponse(true);
    }

    @Data
    @AllArgsConstructor
    static class DeleteToDoResponse {
        private Boolean delete;
    }
}
