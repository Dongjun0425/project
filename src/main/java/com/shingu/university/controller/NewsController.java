package com.shingu.university.controller;

import com.shingu.university.dto.NewsDto;
import com.shingu.university.serviceImpl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsServiceImpl newsService;

    // 뉴스 등록
    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto dto) {
        NewsDto created = newsService.createNews(dto);
        return ResponseEntity.ok(created);
    }

    // 전체 뉴스 목록 조회
    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    // 뉴스 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable int id) {
        NewsDto news = newsService.getNewsById(id);
        return ResponseEntity.ok(news);
    }
}
