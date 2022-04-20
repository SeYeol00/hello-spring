package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//@Service // 에노테이션으로 스프링이 아는 것이다.

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired // 의존관계 주입, dependancy injection 생성자에서 주입
    public MemberService(MemberRepository memberRepository) {// 생성자
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원은 안된다. 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())// 이것 자체가 옵셔널
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    // 회원 한 명 조회

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
