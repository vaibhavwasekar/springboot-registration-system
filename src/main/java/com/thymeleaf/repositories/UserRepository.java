package com.thymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thymeleaf.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
