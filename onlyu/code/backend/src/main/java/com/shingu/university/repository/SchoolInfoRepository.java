package com.shingu.university.repository;

import com.shingu.university.domain.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolInfoRepository extends JpaRepository<SchoolInfo, Long> {
    SchoolInfo findByUniversityId(Long universityId); // Integer → Long으로 수정
}
