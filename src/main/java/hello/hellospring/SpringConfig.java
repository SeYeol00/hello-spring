package hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration // 스프링 빈 등록 방법
// 정형화되지 않은 컴포넌트들을 한데 모아 관리
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * private DataSource dataSource;
     * 
     * @Autowired
     * public SpringConfig(DataSource dataSource) {
     * this.dataSource = dataSource;
     * }
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // @Bean
    // public MemberRepository memberRepository() {
    // // return new MemoryMemberRepository();
    // // return new JdbcMemberRepository(dataSource);
    // // return new JdbcTemplateMemberRepository(dataSource);
    // return new JpaMemberRepository(entityManager);
    // }

    // OCP open closed principle 확장에는 열려있고 변경에는 닫혀있다.
    // 다형성을 통해서 어떤 레포지토리, 어떤 서비스를 사용할 것인가 판단 가능
    // 디펜던시 인젝션을 통해서 조금의 코드 수정으로 구현하고자하는 방식 가능
    // 상속성과 다형성을 이용해서 수정이 가능하여 OOP의 장점이 드러난다.
}
