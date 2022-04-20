package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

//@Repository // 에노테이션으로 스프링이 알아듣는다. 임플리먼트로 상속
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // TODO Auto-generated method stub
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(store.get(id));
        // null 값 존재 가능이므로 널러블
    }

    @Override
    public Optional<Member> findByName(String name) {
        // TODO Auto-generated method stub
        return store.values().stream()
                .filter(member -> member.getName().equals(name))// 람다식
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
