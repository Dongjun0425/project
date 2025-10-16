package com.shingu.university.controller;

import com.shingu.university.domain.StatisticData;
import com.shingu.university.dto.StatisticDataDto;
import com.shingu.university.repository.StatisticDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticDataController {

    private final StatisticDataRepository statisticDataRepository;

    @GetMapping("/{univId}")
    public List<StatisticDataDto> getStats(@PathVariable Long univId) {
        List<StatisticData> dataList = statisticDataRepository.findByUniversityId(univId);

        return dataList.stream()
                .map(d -> new StatisticDataDto(d.getYear(), d.getType(), d.getValue()))
                .toList(); // Java 16+ 또는 .collect(Collectors.toList()) 사용 가능
    }

}
