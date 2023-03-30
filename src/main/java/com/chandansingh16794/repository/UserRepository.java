package com.chandansingh16794.repository;

import com.chandansingh16794.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    Optional<User> findByEmailAndIdNot(String email, Long id);

    Optional<User> findByPhoneAndIdNot(String phone, Long id);
}
