package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
