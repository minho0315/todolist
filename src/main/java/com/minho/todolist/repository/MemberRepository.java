package com.minho.todolist.repository;

import com.minho.todolist.domain.Member;

import java.util.List;

public interface MemberRepository {
    Long save(Member member);

    Member findOne(Long id);

    List<Member> findAll();

    List<Member> findByIdPassword(String userId, String password);

    List<Member> findByUserId(String userId);

    List<Member> findAllWithToDo();
}
