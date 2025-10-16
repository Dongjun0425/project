package com.shingu.university.dto;

import java.util.List;

public record UniversityResponse(
        UniversityDto university,
        List<UniversityEvaluationDto> evaluations
) {}