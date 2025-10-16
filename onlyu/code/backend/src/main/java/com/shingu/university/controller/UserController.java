package com.shingu.university.controller;

import com.shingu.university.dto.UserDto;
import com.shingu.university.service.UserService;
import com.shingu.university.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto request) {
        System.out.println("[로그인 요청] email: " + request.getEmail());

        return userService.login(request.getEmail(), request.getPassword())
                .map(user -> {
                    System.out.println("[로그인 성공] user id: " + user.getId());
                    try {
                        // ✅ user.getId()는 이미 Integer이므로 그대로 사용 (intValue() 불필요)
                        String token = jwtProvider.createToken(user.getId(), user.getEmail());

                        UserDto dto = UserDto.fromEntity(user);
                        System.out.println("[토큰 및 DTO 변환 완료]");

                        return ResponseEntity.ok(Map.of(
                                "token", token,
                                "user", dto
                        ));
                    } catch (Exception e) {
                        System.out.println("[토큰 생성 오류] " + e.getMessage());
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
                    }
                })
                .orElseGet(() -> {
                    System.out.println("[로그인 실패] 이메일 또는 비밀번호 불일치");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
                });
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) { // ✅ Long -> Integer로 변경
        return userService.findById(id)
                .map(UserDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDto dto) { // ✅ Long -> Integer로 변경
        boolean success = userService.updateUser(id, dto);
        return success
                ? ResponseEntity.ok("정보 수정 성공")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("정보 수정 실패");
    }
}