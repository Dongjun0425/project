// ScholarshipDataController.java
package com.shingu.university.controller;

import com.shingu.university.dto.UniversityDto;
import com.shingu.university.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarship")
@RequiredArgsConstructor
public class ScholarshipDataController {

    private final ScholarshipRepository scholarshipRepository;

    @GetMapping("/{univId}")
    public List<UniversityDto.ScholarshipDto> getScholarships(@PathVariable Long univId) {
        return scholarshipRepository.findByUniversityId(univId)
                .stream()
                .map(d -> new UniversityDto.ScholarshipDto(d.getYear(), d.getAmount()))
                .toList();
    }
}
