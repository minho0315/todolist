package com.minho.todolist.service;

import com.minho.todolist.domain.Member;
import com.minho.todolist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public List<Member> findAll() { return memberRepository.findAll(); }

    public List<Member> findByIdPassword(Member member) {
        return memberRepository.findByIdPassword(member.getUserId(), member.getPassword());
    }

}
