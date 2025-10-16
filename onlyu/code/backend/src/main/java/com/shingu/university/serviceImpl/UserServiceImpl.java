package com.shingu.university.serviceImpl;

import com.shingu.university.domain.User;
import com.shingu.university.domain.University;
import com.shingu.university.dto.UserDto;
import com.shingu.university.repository.UserRepository;
import com.shingu.university.repository.UniversityRepository;
import com.shingu.university.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public void saveUser(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setUserType(dto.getUserType());
        user.setDepartmentName(dto.getDepartmentName());
        user.setCreatedAt(LocalDateTime.now());

        if (dto.getUniversityId() != null) {
            // ✅ 수정: dto.getUniversityId()를 Long으로 변환하여 findById 호출
            University university = universityRepository.findById(dto.getUniversityId().longValue()) // ✅ .longValue() 추가
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 대학 ID입니다."));
            user.setUniversity(university);
        } else {
            user.setUniversity(null);
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return userOptional;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean updateUser(Integer id, UserDto dto) {
        return userRepository.findById(id).map(user -> {
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setUserType(dto.getUserType());
            user.setDepartmentName(dto.getDepartmentName());

            if (dto.getUniversityId() != null) {
                // ✅ 수정: dto.getUniversityId()를 Long으로 변환하여 findById 호출
                University university = universityRepository.findById(dto.getUniversityId().longValue()) // ✅ .longValue() 추가
                        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 대학 ID입니다."));
                user.setUniversity(university);
            } else {
                user.setUniversity(null);
            }

            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}