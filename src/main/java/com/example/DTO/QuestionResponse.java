package com.example.DTO;

import lombok.Builder;

@Builder
public record QuestionResponse(
        String subject,
        String content,
        Long authorId,
        String author
) {
}