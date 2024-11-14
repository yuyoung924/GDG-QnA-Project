package com.example.service;

import com.example.DTO.QuestionRequest;
import com.example.DTO.QuestionResponse;
import com.example.DTO.userinfo;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.qna_project.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;


    // 2. 질문 저장
    public Long saveQuestion(
            userinfo userInfo,
            QuestionRequest questionRequest
    ) {
        User author = userRepository.findById(userInfo.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Question question = new Question(questionRequest.subject(), questionRequest.content(), author);
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }


    // 3. 질문 수정
    public Long modifyQuestion(
            userinfo userInfo,
            QuestionRequest questionRequest, Long questionId
    ) {
        User author = userRepository.findById(userInfo.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 질문입니다."));
        // update 메소드 작성
        question.update(questionRequest.subject(), questionRequest.content());
        questionRepository.save(question);
        return question.getId();
    }


    // 4. 질문 삭제
    public Long deleteQuestion(
            userinfo userInfo,
            Long questionId
    ) {
        User author = userRepository.findById(userInfo.getId())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return question.getId();
                })
                .orElseThrow(() -> new RuntimeException("존재하지 않는 질문입니다."));
    }
}