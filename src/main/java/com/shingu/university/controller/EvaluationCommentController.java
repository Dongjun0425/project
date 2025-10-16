package com.shingu.university.controller;

import com.shingu.university.dto.CreateCommentRequest;
import com.shingu.university.dto.EvaluationCommentDTO;
import com.shingu.university.dto.UpdateCommentRequest;
import com.shingu.university.service.EvaluationCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class EvaluationCommentController {

    private final EvaluationCommentService evaluationCommentService;

    public EvaluationCommentController(EvaluationCommentService evaluationCommentService) {
        this.evaluationCommentService = evaluationCommentService;
    }

    @GetMapping("/api/comments")
    public List<EvaluationCommentDTO> getCommentsByEvaluation(@RequestParam Integer evaluationId) {
        return evaluationCommentService.getCommentsByEvaluationId(evaluationId);
    }

    @PostMapping("/api/comments")
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest request) {
        evaluationCommentService.saveComment(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/comments")
    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentRequest request) {
        evaluationCommentService.updateComment(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Integer commentId,
            @RequestParam Integer userId) {

        evaluationCommentService.deleteComment(commentId, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "댓글이 성공적으로 삭제되었습니다.");
        response.put("commentId", commentId);

        return ResponseEntity.ok(response);
    }


}
