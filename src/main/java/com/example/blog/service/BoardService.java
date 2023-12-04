package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Board;
import com.example.blog.model.User;
import com.example.blog.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {// title content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
}
