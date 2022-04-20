package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager entityManager;

    // jpa를 쓸려면 엔티티 메니저가 필요하다
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) { // 저장과 pk기반 쿼리는 간단
        // TODO Auto-generated method stub
        entityManager.persist(member);
        return member; // 이러면 알아서 쿼리 만들어서 넣어줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        // TODO Auto-generated method stub
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override // 이외의 쿼리는 작성 필요
    public Optional<Member> findByName(String name) {
        // TODO Auto-generated method stub
        List<Member> result = entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();// 옵셔널로 바뀜
    }

    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        // jpql, 멤버 엔티티를 조회하고 그대로 반환
        List<Member> result = entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }

}
