package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 스프링 빈으로 등록
public class MemberRepository {

    @PersistenceContext // 스프링이 엔티티매니저를 주입해준다(?
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //jpql 사용(from의 대상이 테이블이 아니라 엔티티
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
