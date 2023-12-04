package com.example.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.model.User;
import java.util.List;

//DAO
//자동으로 빈으로 등록이된다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAll(Pageable pageable);

    List<User> findByUsername(String username);

    // JPA 네이밍 쿼리 전략
    // SELECT * FROM user WHERE username=?1 AND password=?2;
    User findByUsernameAndPassword(String username, String password);

}