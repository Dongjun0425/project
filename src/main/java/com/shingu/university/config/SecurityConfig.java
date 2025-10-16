package com.shingu.university.config;

import com.shingu.university.domain.User;
import com.shingu.university.repository.UserRepository;
import com.shingu.university.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login/**",
                                "/users/**",
                                "/oauth2/**",
                                "/auth/success",
                                "/university/all",
                                "/university/type/**",
                                "/university/rankings/**",
                                "/university/search",
                                "/news",
                                "/api/schoolinfo/**",
                                "/api/tuition/**",
                                "/api/scholarship/**",
                                "/api/statistics/**",
                                "/api/review/**",
                                "/api/evaluations/university/**",
                                "/api/comments/**",
                                "/api/evaluations"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customOAuth2AuthenticationSuccessHandler())
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService())
                        )
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            String email = (String) kakaoAccount.get("email");
            String nickname = (String) profile.get("nickname");

            User user = userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setName(nickname);
                newUser.setPassword("kakao_dummy_password");
                newUser.setCreatedAt(LocalDateTime.now());
                return userRepository.save(newUser);
            });

            // ✅ user.getId()는 이미 Integer이므로 .intValue() 불필요
            String token = jwtProvider.createToken(user.getId(), user.getEmail());

            String encodedUserName = "";
            if (user.getName() != null) {
                try {
                    encodedUserName = URLEncoder.encode(user.getName(), StandardCharsets.UTF_8.toString());
                } catch (UnsupportedEncodingException e) {
                    System.err.println("UTF-8 encoding not supported: " + e.getMessage());
                    encodedUserName = user.getName();
                }
            }

            String redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/kakao-login")
                    .queryParam("token", token)
                    .queryParam("userId", user.getId())
                    .queryParam("userName", encodedUserName)
                    .build().toUriString();

            System.out.println("Redirecting to: " + redirectUrl);
            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return userRequest -> {
            OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
            Map<String, Object> attributes = oauth2User.getAttributes();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            String email = (String) kakaoAccount.get("email");
            String nickname = (String) profile.get("nickname");

            userRepository.findByEmail(email).orElseGet(() -> {
                User user = new User();
                user.setEmail(email);
                user.setName(nickname);
                user.setPassword("kakao_dummy_password");
                user.setCreatedAt(LocalDateTime.now());
                return userRepository.save(user);
            });

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    attributes,
                    "id"
            );
        };
    }
}