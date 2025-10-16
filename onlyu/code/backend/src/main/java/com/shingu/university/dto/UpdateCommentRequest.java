package com.shingu.university.dto;

import lombok.Getter;
import lombok.Setter; // ✅ Setter 추가 (롬복 사용 시)

@Getter
@Setter // ✅ Lombok @Setter 어노테이션 추가
public class UpdateCommentRequest {
    private Long commentId; // 댓글 ID가 Long 타입인 DTO에 맞춤
    private String content;
    private Integer userId; // ✅ 추가: 요청을 보낸 사용자의 ID
}