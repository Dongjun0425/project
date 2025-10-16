package com.shingu.university.controller;

import com.shingu.university.domain.AdmissionStat;
import com.shingu.university.repository.AdmissionStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class AdmissionStatController {

    private final AdmissionStatRepository admissionStatRepository;

    @GetMapping("/admission-pie/{univId}")
    public Map<String, Object> getAdmissionPie(@PathVariable Long univId) {
        List<AdmissionStat> stats = admissionStatRepository.findByUniversityId(univId);

        List<String> labels = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        for (AdmissionStat stat : stats) {
            labels.add(stat.getEntranceType());       // "정시", "수시" 등
            values.add(stat.getRegistrationRate());   // 88.4 등
        }

        Map<String, Object> result = new HashMap<>();
        result.put("labels", labels);
        result.put("values", values);

        return result;
    }
}
