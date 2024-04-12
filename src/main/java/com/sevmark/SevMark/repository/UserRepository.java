package com.sevmark.SevMark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevmark.SevMark.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
