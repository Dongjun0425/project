package com.shingu.university.repository;

import com.shingu.university.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // ✅ User ID 타입이 Integer이므로 여기는 유지

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"university", "department"})
    Optional<User> findById(Integer id); // ✅ Long -> Integer로 변경
}