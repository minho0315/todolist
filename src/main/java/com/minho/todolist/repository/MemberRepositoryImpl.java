package com.minho.todolist.repository;

import com.minho.todolist.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Long save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member.getId();
    }

    @Override
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() { return em.createQuery("select m from Member m", Member.class).getResultList(); }

    @Override
    public List<Member> findByIdPassword(String userId, String password) {
        return em.createQuery("select m from Member m where m.userId = :userId AND m.password = :password", Member.class)
                .setParameter("userId", userId)
                .setParameter("password", password)
                .getResultList();
    }

    @Override
    public List<Member> findByUserId(String userId) {
        return em.createQuery("select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Member> findAllWithToDo() {
        return em.createQuery(
                        "select distinct m from Member m" +
                                " join fetch m.toDos oi", Member.class)
                .getResultList();
    }
}
