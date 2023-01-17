package com.example.barcoder.user.domain.repository;

import com.example.barcoder.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByUsernameOrPhoneNumber(String username, String phoneNumber);

    boolean existsByUsername(String username);
}
