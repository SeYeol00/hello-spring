package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // LocalHost 8080으로 들어오면 가장 먼저 나오는 페이지
    public String home() {
        return "home";
    }

}
