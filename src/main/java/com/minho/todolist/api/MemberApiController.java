package com.minho.todolist.api;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;
import com.minho.todolist.domain.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepository memberRepository;

    @GetMapping("/api/members")
    public List<MemberDto> getMembers() {
        List<Member> members = memberRepository.findAllWithToDo();
        List<MemberDto> result = members.stream()
                .map(m -> new MemberDto(m))
                .collect(toList());
        return result;
    }

    @Data
    static class MemberDto {

        private Long memberId;
        private String username;
        private String userId;
        private String password;
        private List<ToDoDto> toDos;

        public MemberDto(Member member) {
            memberId = member.getId();
            username = member.getUsername();
            userId = member.getUserId();
            password = member.getPassword();
            toDos = member.getToDos().stream()
                    .map(toDo -> new ToDoDto(toDo))
                    .collect(toList());
        }
    }

    @Data
    static class ToDoDto {
        private String content;
        private Boolean state;

        public ToDoDto(ToDo toDo) {
            content = toDo.getContent();
            state = toDo.getState();
        }
    }
}
