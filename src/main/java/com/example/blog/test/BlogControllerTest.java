package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 스프링이 com.example.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는게 아니라.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IOC)스프링 컨테이너에 관리해준다.

// Contoller 사용시 html 호환가능
@Controller
public class BlogControllerTest {
    @GetMapping("/test/hellooo")
    public String hello() {
        System.out.println();
        return "html/test";
    }
}
