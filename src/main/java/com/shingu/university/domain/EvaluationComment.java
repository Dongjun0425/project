package com.shingu.university.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList; // List 초기화를 위해 추가
import java.util.List; // 대댓글 관계를 위해 추가될 수 있음

@Entity
@Table(name = "evaluation_comments") // 테이블 이름이 없다면 추가하거나, 실제 DB 테이블 이름과 일치시키세요.
@Getter
@Setter
public class EvaluationComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id")
    private UniversityEvaluation evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private EvaluationComment parent; // null이면 댓글, 있으면 대댓글

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_edited", nullable = false)
    private boolean isEdited;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false) // ✅ 추가: 삭제 여부 필드
    private boolean isDeleted = false; // 기본값 false로 설정

    // @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<EvaluationComment> replies = new ArrayList<>(); // 자식 댓글 컬렉션 (필요하다면 추가)
    // 이 필드는 대댓글의 계층 구조를 EAGER 로딩하거나 관계를 직접 탐색할 때 유용하지만,
    // 현재 서비스 로직에서 Map을 통해 계층을 구성하므로 필수적이지 않을 수 있습니다.
    // 하지만 ORM 관점에서 양방향 관계를 설정하는 것은 좋은 방법입니다.
    // 만약 replies를 사용하지 않더라도 isDeleted 필드는 여전히 필요합니다.
}