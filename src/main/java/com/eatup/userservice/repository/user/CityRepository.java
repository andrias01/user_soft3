package com.eatup.userservice.repository.user;

import com.eatup.userservice.domain.user.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
	boolean existsByIdAndDepartmentId(UUID id, UUID departmentId);
}