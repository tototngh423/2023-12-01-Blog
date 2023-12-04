package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/blog/join")
    public String joinForm() {
        return "html/join";
    }

    @GetMapping("/blog/login")
    public String loginForm() {
        return "html/login";
    }

    @GetMapping("/blog/write")
    public String writeForm() {
        return "html/write";
    }
}
