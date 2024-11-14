package com.example.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    // TODO : 질문 리스트 매핑

    public Question(String subject, String content, User author) {
        // id는 JPA가 자동으로 생성하여 DB에 저장
        this.subject = subject;
        this.content = content;
        this.author = author;
    }

    public void update(
            String subject,
            String content
    ) {
        this.subject = subject;
        this.content = content;
    }


}


