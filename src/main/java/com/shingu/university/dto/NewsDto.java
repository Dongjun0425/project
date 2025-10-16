package com.shingu.university.dto;

import com.shingu.university.domain.News;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {

    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private String category; // ✅ 추가된 카테고리 필드
    private LocalDateTime createdAt;

    public static NewsDto fromEntity(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .imageUrl(news.getImageUrl())
                .category(news.getCategory()) // ✅ 추가된 변환 코드
                .createdAt(news.getCreatedAt())
                .build();
    }
}
