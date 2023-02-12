package com.minho.todolist.repository;

import com.minho.todolist.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

}
