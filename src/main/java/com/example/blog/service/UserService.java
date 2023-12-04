package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.IoC를 해준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true) // Select할때 트랜젝션 시작, 서비스 종료시에 트랜잭션 종료
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
        // try {

        // return 1;
        // } catch (Exception e) {
        // e.printStackTrace();
        // System.out.println("UserService : 회원가입() : " + e.getMessage());
        // }
        // return -1;
    }
}
