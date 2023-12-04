package com.example.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터를 쓸때 사용
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨

    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // Many=Board, User=one EAGER= board테이블 셀렉하면 user테이블 정보 가져와줄께
    @JoinColumn(name = "userId") // User.java를 참조함으로써 자동으로 FK가된다.
    private User user; // DB는 오브젝트를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다. (난 FK아니다) 유에 칼럼을 만들지 마세여
    private List<Reply> reply; // 10건이 될수도 만건이될수도 답글이.

    @CreationTimestamp
    private Timestamp createDate;

}
