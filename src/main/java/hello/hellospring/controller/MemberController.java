package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller // 에노테이션으로 스프링이 아는 것이다.
public class MemberController {

    private final MemberService memberService;
    // controller는 스프링 컨테이너에 등록을 하고 쓰는게 좋다.

    @Autowired
    // 스프링에 있는 맴버 서비스를 자동으로 연결시켜줌 컴포넌트 스캔 방식
    // 컨트롤러는 오토와이어가 필요 생성자로 주입하는게 트렌드
    // 에노테이션으로 의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService: " + memberService.getClass());
        // 프록시 확인
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
        // 리다이렉트로 회원가입이 끝나면 포스트로 데이터는 보내주고 홈으로 돌아가기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}// 컨트롤러, 서비스, 레포지토리는 정형화 되어있는 패턴이다.
 // 정형화 되어있는 컴포넌트들은 의존 관계 주입을하고(D.I)
 // 정형화 되지 않은 컴포넌트들은 스프링 빈을 등록하는 것이 좋다.
