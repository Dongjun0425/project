package com.shingu.university.dto;

public class CreateCommentRequest {

    private Integer evaluationId;
    private Integer parentId;  // 대댓글일 경우 상위 댓글 ID, 일반 댓글이면 null
    private String content;
    private Integer userId;

    public CreateCommentRequest() {
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
