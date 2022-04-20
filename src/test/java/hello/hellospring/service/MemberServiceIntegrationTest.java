package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

@WebAppConfiguration
@SpringBootTest // 두 어노테이션이 핵심
@Transactional // 테스트를 실행할 때 트렌젝션을 실행하고 끝나면 롤백이 됨
class MemberServiceIntegrationTest {
    // 디비까지 연결해서 돌리는게 통합 테스트

    @Autowired // 테스트에서는 굳이 객체 생성하지 말고 그냥 DI 써서 하는게 편하다.
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {

        // given
        Member member = new Member();
        member.setName("spring");
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
