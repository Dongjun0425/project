package com.shingu.university.dto;

import com.shingu.university.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id; // ✅ int -> Integer로 변경 (User 엔티티 id와 일치)
    private String email;
    private String password;
    private String name;
    private String userType;
    private Integer universityId;
    private String universityName;
    private Integer departmentId;
    private String departmentName;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId()) // User.id가 Integer이므로 그대로 사용
                .email(user.getEmail())
                .name(user.getName())
                .userType(user.getUserType())
                .universityId(user.getUniversity() != null ? user.getUniversity().getId() : null)
                .universityName(user.getUniversity() != null ? user.getUniversity().getName() : null)
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .departmentName(user.getDepartmentName() != null ? user.getDepartmentName() :
                        user.getDepartment() != null ? user.getDepartment().getName() : null)
                .build();
    }
}