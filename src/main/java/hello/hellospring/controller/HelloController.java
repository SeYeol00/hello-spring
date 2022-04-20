package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // 서버 뒤에 슬래시 매핑키를 치면 나옴
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!");
        // 키값 벨류값
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name", required = false) String name) {
        return "hello" + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody // json으로 반환이 기본
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 생성후 넘기기

    }

    static class Hello {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        };

    }
    // 객체는 json 형식으로 반환해준다.
    // 정적 컨탠츠는 파일 그대로
    //
    // ctrl + f5 => 실행
    // Getter & Setter를 만들 변수를 드래그로 선택해서 명령팔레트(F1키 또는 Ctrl + Shift + p)에서 generate 을
    // 입력
    // 하고
    // Generate get and set methods를 선택 한다.
}
