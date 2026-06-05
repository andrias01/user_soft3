package com.eatup.userservice.service.user;

import com.eatup.userservice.repository.user.CityRepository;
import com.eatup.userservice.repository.user.DepartmentRepository;
import com.eatup.userservice.repository.user.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CatalogValidationService {

	private final DocumentTypeRepository documentTypeRepository;
	private final DepartmentRepository departmentRepository;
	private final CityRepository cityRepository;

	public CatalogValidationService(DocumentTypeRepository documentTypeRepository,
							 DepartmentRepository departmentRepository,
							 CityRepository cityRepository) {
		this.documentTypeRepository = documentTypeRepository;
		this.departmentRepository = departmentRepository;
		this.cityRepository = cityRepository;
	}

	public void validateReferences(UUID documentTypeId, UUID departmentId, UUID cityId) {
		if (!documentTypeRepository.existsById(documentTypeId)) {
			throw new IllegalArgumentException("Selected document type does not exist");
		}
		if (!departmentRepository.existsById(departmentId)) {
			throw new IllegalArgumentException("Selected department does not exist");
		}
		if (!cityRepository.existsById(cityId)) {
			throw new IllegalArgumentException("Selected city does not exist");
		}
		if (!cityRepository.existsByIdAndDepartmentId(cityId, departmentId)) {
			throw new IllegalArgumentException("Selected city does not belong to the selected department");
		}
	}
}