package com.example.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 sql테이블이 생성
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스 auto)increment

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100) // 123456 => 해쉬사용하여(비번 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'")
    // DB는 RoleType이란게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role;// Enum을 쓰는게 좋다. ADMIN,USER,manager(m)

    @CreationTimestamp // 시간이 자동입력
    private Timestamp createDate;
}
