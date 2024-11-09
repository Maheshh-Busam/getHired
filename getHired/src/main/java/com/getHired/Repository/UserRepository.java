package com.getHired.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getHired.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
