package com.shingu.university.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 엔드포인트에 대해
                .allowedOrigins("http://localhost:5173")  // 프론트엔드 개발 서버 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 허용 메서드
                .allowedHeaders("*")  // 모든 헤더 허용
                .allowCredentials(true);  // 쿠키/세션 등의 자격 정보 포함 허용
    }
}
