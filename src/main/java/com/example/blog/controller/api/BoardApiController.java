package com.example.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;
import com.example.blog.model.Board;
import com.example.blog.service.BoardService;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board) {
        boardService.글쓰기(board, null);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);// 자바오브젝트를 JSON으로 변환해서 리턴
    }
}
