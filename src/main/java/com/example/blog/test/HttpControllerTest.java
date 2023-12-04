package com.example.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Contoller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String Tag = "HttpController:";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        // new Member("ssar","1234","email")
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();

        System.out.println(Tag + "getter:" + m.getUsername());
        m.setUsername("suho");
        System.out.println(Tag + "setter:" + m.getUsername());
        return "lombok테스트완료";
    }

    // 인터넷 브라우저 요청은 무조건 get요청 밖에 할수 없다.
    // (select)
    @GetMapping("/http/get") // MessageConverter(스프링부트)
    public String getTest(Member m) {// ?id=1&username=suho&password=1234&email=ssar@nate.com
        return "get요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (insert) // postman에서 실행
    @PostMapping("/http/post") // text/plain String text ;+ text;, application/json
    public String postTest(@RequestBody Member m) { // MessageConverter(스프링부트)
        return "post요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete요청";
    }

}
