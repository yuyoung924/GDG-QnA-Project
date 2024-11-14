package com.example.controller;

import config.arumentresolver.Login;

import com.example.DTO.QuestionRequest;
import com.example.DTO.QuestionResponse;
import com.example.DTO.userinfo;
import com.example.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long questionId) {
        QuestionResponse response = questionService.getQuestion(questionId);
        return ResponseEntity.ok(response);
    }


    // 2. 질문 등록
    @PostMapping
    public ResponseEntity<Long> createQuestion(
            @Login userinfo userInfo,
            @RequestBody QuestionRequest questionRequest
    ) {
        Long questionId = questionService.saveQuestion(userInfo, questionRequest);
        return ResponseEntity.ok(questionId);
    }


    // 3. 질문 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity<Long> modifyQuestion(
            @Login userinfo userInfo,
            @PathVariable Long questionId,
            @RequestBody QuestionRequest questionRequest
    ) {
        Long updatedQuestionId = questionService.modifyQuestion(userInfo, questionRequest, questionId);
        return ResponseEntity.ok(updatedQuestionId);
    }


    // 4. 질문 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Long> deleteQuestion(
            @Login userinfo userInfo,
            @PathVariable Long questionId
    ) {
        Long deletedQuestionId = questionService.deleteQuestion(userInfo, questionId);
        return ResponseEntity.ok(deletedQuestionId);
    }
}
