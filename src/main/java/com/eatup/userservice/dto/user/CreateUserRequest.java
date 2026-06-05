package com.eatup.userservice.dto.user;

import com.eatup.userservice.util.user.validation.UserValidationRules;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public class CreateUserRequest {

	@NotBlank(message = "Field 'firstName' is required")
	@Size(min = UserValidationRules.NAME_MIN_LENGTH, max = UserValidationRules.NAME_MAX_LENGTH,
			message = "Field 'firstName' must be between 2 and 100 characters")
	@Pattern(regexp = UserValidationRules.NAME_REGEX,
			message = "Field 'firstName' contains invalid characters")
	private String firstName;

	@NotBlank(message = "Field 'lastName' is required")
	@Size(min = UserValidationRules.NAME_MIN_LENGTH, max = UserValidationRules.NAME_MAX_LENGTH,
			message = "Field 'lastName' must be between 2 and 100 characters")
	@Pattern(regexp = UserValidationRules.NAME_REGEX,
			message = "Field 'lastName' contains invalid characters")
	private String lastName;

	@NotNull(message = "Field 'documentTypeId' is required")
	private UUID documentTypeId;

	@NotBlank(message = "Field 'documentNumber' is required")
	@Size(min = UserValidationRules.DOCUMENT_NUMBER_MIN_LENGTH,
			max = UserValidationRules.DOCUMENT_NUMBER_MAX_LENGTH,
			message = "Field 'documentNumber' must be between 5 and 30 characters")
	@Pattern(regexp = UserValidationRules.DOCUMENT_NUMBER_REGEX,
			message = "Field 'documentNumber' contains invalid characters")
	private String documentNumber;

	@NotBlank(message = "Field 'phone' is required")
	@Pattern(regexp = UserValidationRules.PHONE_REGEX,
			message = "Field 'phone' must contain exactly 10 digits")
	private String phone;

	@NotBlank(message = "Field 'email' is required")
	@Size(max = UserValidationRules.EMAIL_MAX_LENGTH,
			message = "Field 'email' must be at most 150 characters")
	@Pattern(regexp = UserValidationRules.EMAIL_REGEX,
			message = "Field 'email' must contain a valid email address")
	private String email;

	@NotBlank(message = "Field 'password' is required")
	@Size(min = UserValidationRules.PASSWORD_MIN_LENGTH, max = UserValidationRules.PASSWORD_MAX_LENGTH,
			message = "Field 'password' must be between 8 and 72 characters")
	@Pattern(regexp = UserValidationRules.PASSWORD_REGEX,
			message = "Field 'password' must contain uppercase, lowercase, number and special character")
	private String password;

	@NotNull(message = "Field 'birthDate' is required")
	@Past(message = "Field 'birthDate' must be a past date")
	private LocalDate birthDate;

	@NotNull(message = "Field 'departmentId' is required")
	private UUID departmentId;

	@NotNull(message = "Field 'cityId' is required")
	private UUID cityId;

	@NotBlank(message = "Field 'address' is required")
	@Size(min = UserValidationRules.ADDRESS_MIN_LENGTH, max = UserValidationRules.ADDRESS_MAX_LENGTH,
			message = "Field 'address' must be between 5 and 255 characters")
	@Pattern(regexp = UserValidationRules.ADDRESS_REGEX,
			message = "Field 'address' contains invalid characters")
	private String address;

	@NotNull(message = "Field 'locationId' is required")
	private UUID locationId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UUID getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(UUID documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public UUID getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(UUID departmentId) {
		this.departmentId = departmentId;
	}

	public UUID getCityId() {
		return cityId;
	}

	public void setCityId(UUID cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}
}