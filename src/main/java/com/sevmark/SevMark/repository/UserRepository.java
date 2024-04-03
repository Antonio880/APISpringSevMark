package com.sevmark.SevMark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevmark.SevMark.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
