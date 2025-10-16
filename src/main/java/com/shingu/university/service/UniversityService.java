package com.shingu.university.service;

import com.shingu.university.domain.University;
import com.shingu.university.dto.UniversityDto;
import com.shingu.university.dto.UniversityRankingDto;
import com.shingu.university.dto.UniversityResponse;

import java.util.List;
import java.util.Map;

public interface UniversityService {
    public List<UniversityDto> getAllUniversity();
    public UniversityResponse getUniversityDetail(int id);
    public List<UniversityDto> getUniversitiesByType(String type);
    public Map<String, List<UniversityRankingDto>> getAllCategoryRankings();
    public List<UniversityDto> searchUniversitiesByName(String keyword);

}
