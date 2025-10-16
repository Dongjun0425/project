package com.shingu.university.repository;

import com.shingu.university.domain.EvaluationComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationCommentRepository extends JpaRepository<EvaluationComment, Integer> {
    List<EvaluationComment> findByEvaluationIdOrderByCreatedAtAsc(Integer evaluationId);
}
