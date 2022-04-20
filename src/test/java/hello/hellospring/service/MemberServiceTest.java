package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트를 실행하기 전
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // dependancy injection
        // 의존 관계 주입
    }

    @AfterEach // 각 테스트를 실행한 후
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        // Member 객체로 나옴
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        // 테스트 할 때 구분하는 3종 세트

    }

    @Test
    public void 중복회원예외검사() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        /*
         * IllegalStateException e =
         * Assertions.assertThrows(IllegalStateException.class,
         * () -> memberService.join(member2));
         * Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
         */
        /*
         * try {
         * memberService.join(member2);
         * fail();
         * } catch (IllegalStateException e) {
         * Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
         * }
         */

    }

    @Test
    void testFindOne() {

    }

    @Test
    void testJoin() {

    }
}
