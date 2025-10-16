    package com.shingu.university.dto;

    import com.shingu.university.domain.University;
    import lombok.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class UniversityDto {
        private Integer id; // 학교 고유 ID
        private String name;
        private String description;
        private String location;
        private String type;
        private Double latitude;
        private Double longitude;
        private String imageUrl; // 추가된 이미지 URL 필드
        private String code;


        public record ScholarshipDto(Integer year, Double amount) {}
        public static UniversityDto fromEntity(University university) {
            return new UniversityDto(
                    university.getId(),
                    university.getName(),
                    university.getDescription(),
                    university.getLocation(),
                    university.getType(),
                    university.getLatitude(),
                    university.getLongitude(),
                    university.getImageUrl(),
                    university.getCode()
            );
        }
    }