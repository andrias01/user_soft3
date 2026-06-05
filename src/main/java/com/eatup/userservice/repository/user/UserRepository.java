package com.eatup.userservice.repository.user;

import com.eatup.userservice.domain.user.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, UUID> {
	boolean existsByEmailIgnoreCase(String email);
	Optional<UserDomain> findById(UUID id);
}