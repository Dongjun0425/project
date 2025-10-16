package com.shingu.university.dto;

import com.shingu.university.domain.SchoolInfo;
import com.shingu.university.domain.University;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UniversityDetailDto {

    private Integer id;
    private String name;
    private String location;
    private String code;
    private String type;
    private String imageUrl;

    // 학교 상세 정보 (순서 맞춤)
    private String schlEstbDivNm;      // 설립유형
    private String schlDivNm;          // 학교유형
    private String schlEstbDt;         // 설립연도
    private String postNoAdrs;         // 주소
    private String postNo;             // 우편번호
    private String schlRepTpNoCtnt;    // 전화번호
    private String schlRepFxNoCtnt;    // 팩스번호
    private String schlUrlAdrs;        // 홈페이지

    public static UniversityDetailDto from(University university, SchoolInfo schoolInfo) {
        return UniversityDetailDto.builder()
                .id(university.getId())
                .name(university.getName())
                .location(university.getLocation())
                .code(university.getCode())
                .type(university.getType())
                .imageUrl(university.getImageUrl())

                // SchoolInfo에서 가져옴
                .schlEstbDivNm(schoolInfo != null ? schoolInfo.getSchlEstbDivNm() : null)
                .schlDivNm(schoolInfo != null ? schoolInfo.getSchlDivNm() : null)
                .schlEstbDt(schoolInfo != null ? schoolInfo.getSchlEstbDt() : null)
                .postNoAdrs(schoolInfo != null ? schoolInfo.getPostNoAdrs() : null)
                .postNo(schoolInfo != null ? schoolInfo.getPostNo() : null)
                .schlRepTpNoCtnt(schoolInfo != null ? schoolInfo.getSchlRepTpNoCtnt() : null)
                .schlRepFxNoCtnt(schoolInfo != null ? schoolInfo.getSchlRepFxNoCtnt() : null)
                .schlUrlAdrs(schoolInfo != null ? schoolInfo.getSchlUrlAdrs() : null)
                .build();
    }
}
