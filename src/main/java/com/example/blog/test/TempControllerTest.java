package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    @GetMapping("/temp/home")
    public String tempHome() {
        return "html/home";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "html/img";
    }
}
