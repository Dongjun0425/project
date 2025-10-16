package com.shingu.university.service;

import com.shingu.university.dto.NewsDto;

import java.util.List;

public interface NewsService {
    public NewsDto createNews(NewsDto dto);
    public List<NewsDto> getAllNews();
    public NewsDto getNewsById(int id);
}
