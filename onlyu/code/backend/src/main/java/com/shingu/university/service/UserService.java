package com.shingu.university.service;

import com.shingu.university.domain.User;
import com.shingu.university.dto.UserDto;

import java.util.Optional;

public interface UserService {
    void saveUser(UserDto dto);
    Optional<User> login(String email, String password);
    Optional<User> findById(Integer id);

    // ✅ 사용자 정보 수정
    boolean updateUser(Integer id, UserDto dto);
}
