package com.eatup.userservice.service.user;

import com.eatup.userservice.domain.user.UserDomain;
import com.eatup.userservice.domain.user.UserStatus;
import com.eatup.userservice.dto.user.CreateUserRequest;
import com.eatup.userservice.dto.user.UpdateUserRequest;
import com.eatup.userservice.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserCommandService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CatalogValidationService catalogValidationService;

	public UserCommandService(UserRepository userRepository,
						 PasswordEncoder passwordEncoder,
						 CatalogValidationService catalogValidationService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.catalogValidationService = catalogValidationService;
	}

	@Transactional
	public void createUser(CreateUserRequest request) {
		validateDuplicateEmail(request.getEmail());
		catalogValidationService.validateReferences(request.getDocumentTypeId(), request.getDepartmentId(), request.getCityId());

		UserDomain user = new UserDomain();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setDocumentTypeId(request.getDocumentTypeId());
		user.setDocumentNumber(request.getDocumentNumber());
		user.setPhone(request.getPhone());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setBirthDate(request.getBirthDate());
		user.setDepartmentId(request.getDepartmentId());
		user.setCityId(request.getCityId());
		user.setAddress(request.getAddress());
		user.setLocationId(request.getLocationId());
		user.setStatus(UserStatus.ACTIVE);
		user.setCreatedAt(LocalDateTime.now());
		user.setModifiedAt(LocalDateTime.now());

		userRepository.save(user);
	}

	@Transactional
	public void updateUser(UUID userId, UpdateUserRequest request) {
		UserDomain existing = findUserById(userId);
		catalogValidationService.validateReferences(request.getDocumentTypeId(), request.getDepartmentId(), request.getCityId());

		existing.setFirstName(request.getFirstName());
		existing.setLastName(request.getLastName());
		existing.setDocumentTypeId(request.getDocumentTypeId());
		existing.setDocumentNumber(request.getDocumentNumber());
		existing.setPhone(request.getPhone());
		existing.setBirthDate(request.getBirthDate());
		existing.setDepartmentId(request.getDepartmentId());
		existing.setCityId(request.getCityId());
		existing.setAddress(request.getAddress());
		existing.setLocationId(request.getLocationId());
		existing.setModifiedAt(LocalDateTime.now());

		userRepository.save(existing);
	}

	@Transactional
	public void updateStatus(UUID userId, String status) {
		UserDomain existing = findUserById(userId);
		existing.setStatus(parseStatus(status));
		existing.setModifiedAt(LocalDateTime.now());
		userRepository.save(existing);
	}

	private UserDomain findUserById(UUID userId) {
		if (userId == null) {
			throw new IllegalArgumentException("User id is required");
		}
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	private void validateDuplicateEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Field 'email' is required");
		}
		if (userRepository.existsByEmailIgnoreCase(email.trim().toLowerCase(Locale.ROOT))) {
			throw new IllegalStateException("Request could not be processed");
		}
	}

	private UserStatus parseStatus(String status) {
		if (status == null || status.isBlank()) {
			throw new IllegalArgumentException("Field 'status' is required and cannot be empty");
		}
		try {
			return UserStatus.valueOf(status.trim().toUpperCase(Locale.ROOT));
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Field 'status' must be one of: ACTIVE, INACTIVE");
		}
	}
}