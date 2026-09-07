package com.pro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.entity.UserRegistration;

@Repository
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Long> {

	UserRegistration findByEmail(String email);

}
