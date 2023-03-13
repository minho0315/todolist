package com.minho.todolist.repository;

import com.minho.todolist.domain.Member;
import com.minho.todolist.domain.ToDo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeMemberRepository implements MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Long save(Member member) {
        store.put(++sequence, member);
        member.setId(sequence);
        return sequence;
    }

    @Override
    public Member findOne(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return store.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Member> findByIdPassword(String userId, String password) {
        return store.values().stream()
                .filter(m -> m.getUserId() == userId && m.getPassword() == password)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findByUserId(String userId) {
        return store.values().stream()
                .filter(m -> m.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findAllWithToDo() {
        return store.values().stream().collect(Collectors.toList());
    }
}
