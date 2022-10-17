package com.minho.todolist.service;

import com.minho.todolist.domain.Member;

public interface MemberService {
    void join(Member member);
    Member findByMember(Long userId);
}
