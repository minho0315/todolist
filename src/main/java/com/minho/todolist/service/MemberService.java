package com.minho.todolist.service;

import com.minho.todolist.domain.Member;

public interface MemberService {
    Long join(Member member);
    Member findByMember(Long userId);
}
