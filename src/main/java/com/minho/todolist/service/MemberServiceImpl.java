package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import com.minho.todolist.repository.MemberRepository;
import com.minho.todolist.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Long join(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Override
    public Member findByMember(Long id) {
        return memberRepository.findById(id);
    }
}
