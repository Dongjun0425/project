package com.shingu.university.service;

import com.shingu.university.domain.EvaluationComment;
import com.shingu.university.domain.UniversityEvaluation;
import com.shingu.university.domain.User;
import com.shingu.university.dto.CreateCommentRequest;
import com.shingu.university.dto.EvaluationCommentDTO;
import com.shingu.university.dto.UpdateCommentRequest;
import com.shingu.university.exception.PermissionDeniedException;
import com.shingu.university.repository.EvaluationCommentRepository;
import com.shingu.university.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EvaluationCommentService {

    private final EvaluationCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UniversityEvaluationService universityEvaluationService;

    public EvaluationCommentService(EvaluationCommentRepository commentRepository, UserRepository userRepository, UniversityEvaluationService universityEvaluationService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.universityEvaluationService = universityEvaluationService;
    }

    public List<EvaluationCommentDTO> getCommentsByEvaluationId(Integer evaluationId) {
        List<EvaluationComment> allComments = commentRepository.findByEvaluationIdOrderByCreatedAtAsc(evaluationId);

        Map<Long, EvaluationCommentDTO> commentMap = new HashMap<>();
        List<EvaluationCommentDTO> rootComments = new ArrayList<>();

        for (EvaluationComment comment : allComments) {
            EvaluationCommentDTO dto = new EvaluationCommentDTO(
                    comment.getId() != null ? comment.getId().longValue() : null, // EvaluationCommentDTO의 ID는 Long
                    comment.getContent(),
                    comment.getUser() != null ? comment.getUser().getName() : null,
                    comment.getParent() != null ? comment.getParent().getId().longValue() : null, // Parent ID는 Long
                    comment.isEdited(),
                    comment.getUser() != null ? comment.getUser().getId() : null, // User.id는 Integer
                    comment.getCreatedAt(),
                    comment.getUpdatedAt(),
                    comment.isDeleted()
            );
            commentMap.put(dto.getId(), dto);
        }

        for (EvaluationCommentDTO commentDTO : commentMap.values()) {
            if (commentDTO.getParentId() == null) {
                rootComments.add(commentDTO);
            } else {
                EvaluationCommentDTO parentDTO = commentMap.get(commentDTO.getParentId());
                if (parentDTO != null) {
                    parentDTO.getReplies().add(commentDTO);
                }
            }
        }
        return rootComments;
    }

    @Transactional
    public void saveComment(CreateCommentRequest request) {
        EvaluationComment comment = new EvaluationComment();
        comment.setContent(request.getContent());
        comment.setEdited(false);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setDeleted(false);

        UniversityEvaluation evaluation = universityEvaluationService.findById(request.getEvaluationId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 평가 ID입니다."));
        comment.setEvaluation(evaluation);

        // ✅ 수정: request.getUserId()가 Integer이고, userRepository.findById가 Integer를 받으므로 longValue() 제거
        User user = userRepository.findById(request.getUserId()) // .longValue() 제거
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
        comment.setUser(user);

        if (request.getParentId() != null) {
            EvaluationComment parentComment = commentRepository.findById(request.getParentId().intValue())
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 부모 댓글 ID입니다."));
            comment.setParent(parentComment);
        }

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest request) {
        // request.getCommentId()는 Long이므로 intValue()로 변환
        EvaluationComment comment = commentRepository.findById(request.getCommentId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // ✅ 수정: request.getUserId()가 Integer이고, comment.getUser().getId()도 Integer이므로 그대로 비교
        if (comment.getUser() == null || !comment.getUser().getId().equals(request.getUserId())) { // .longValue() 제거
            throw new PermissionDeniedException("수정 권한이 없습니다.");
        }
        if (comment.isDeleted()) {
            throw new IllegalArgumentException("삭제된 댓글은 수정할 수 없습니다.");
        }

        comment.setContent(request.getContent());
        comment.setEdited(true);
        comment.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional
    public void deleteComment(Integer commentId, Integer userId) {
        EvaluationComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // ✅ 수정: userId가 Integer이고, comment.getUser().getId()도 Integer이므로 그대로 비교
        if (comment.getUser() == null || !comment.getUser().getId().equals(userId)) { // Long.valueOf() 제거
            throw new PermissionDeniedException("삭제 권한이 없습니다.");
        }
        if (comment.isDeleted()) {
            throw new IllegalArgumentException("이미 삭제된 댓글입니다.");
        }

        comment.setDeleted(true);
        comment.setContent("삭제된 댓글입니다.");
        comment.setEdited(false);
        comment.setUpdatedAt(LocalDateTime.now());
    }
}