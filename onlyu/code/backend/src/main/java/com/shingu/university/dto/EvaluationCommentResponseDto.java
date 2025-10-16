package com.shingu.university.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EvaluationCommentResponseDto {
    private Integer id;
    private String content;
    private boolean isEdited;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
    private Integer parentId;
}
