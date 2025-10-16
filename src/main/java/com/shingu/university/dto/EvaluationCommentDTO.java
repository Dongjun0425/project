package com.shingu.university.dto;

import lombok.Builder; // @Builder 어노테이션은 필드를 모두 포함하는 생성자가 있으면 자동으로 작동하므로 주석처리 해제 가능
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
// @Builder // 모든 필드를 포함하는 생성자가 명시되었으므로 @Builder는 선택사항입니다.
public class EvaluationCommentDTO {
    private Long id;
    private String content;
    private String userName; // 댓글 작성자 이름
    private Long parentId;
    private boolean isEdited;
    private Integer userId; // 작성자 userId (프론트에서 권한 확인용)
    private LocalDateTime createdAt; // 작성 시간 (프론트에서 표시용)
    private LocalDateTime updatedAt; // 수정 시간 (프론트에서 표시용)
    private List<EvaluationCommentDTO> replies; // 자식 댓글 목록

    private boolean isDeleted; // ✅ 추가: 삭제 여부 필드

    // 기존에 사용되던 생성자 (필요하다면 유지)
    public EvaluationCommentDTO(Long id, String content, String userName, Long parentId, boolean isEdited) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.parentId = parentId;
        this.isEdited = isEdited;
        this.replies = new ArrayList<>();
    }

    // 모든 필드를 포함하는 생성자 (EvaluationCommentService에서 사용될 생성자)
    // ✅ isDeleted 필드를 파라미터에 추가합니다.
    public EvaluationCommentDTO(Long id, String content, String userName, Long parentId, boolean isEdited, Integer userId, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.parentId = parentId;
        this.isEdited = isEdited;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted; // ✅ isDeleted 필드 초기화
        this.replies = new ArrayList<>();
    }
}