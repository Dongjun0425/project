package com.shingu.university.serviceImpl;

import com.shingu.university.domain.News;
import com.shingu.university.dto.NewsDto;
import com.shingu.university.repository.NewsRepository;
import com.shingu.university.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsDto createNews(NewsDto dto) {
        News news = News.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .imageUrl(dto.getImageUrl())
                .category(dto.getCategory()) // ✅ category 필드 반영
                .build();
        return NewsDto.fromEntity(newsRepository.save(news));
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(NewsDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto getNewsById(int id) {
        return newsRepository.findById(id)
                .map(NewsDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("해당 뉴스가 존재하지 않습니다. ID = " + id));
    }
}
