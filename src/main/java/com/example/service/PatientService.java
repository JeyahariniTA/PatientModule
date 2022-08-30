package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.model.Patient;
import com.example.model.PatientDto;

public interface PatientService {

	@Cacheable(value = "PatientsCache")
	public List<PatientDto> listPatients();

	public List<PatientDto> listPatientsWithPagination(int pageNo, int usersCount);

	@Cacheable(value = "PatientsCache", key = "#id")
	public PatientDto getPatientById(int id);

	public PatientDto addPatient(Patient user);

	@CacheEvict(value = "PatientsCache", key = "#id")
	public String deletePatient(int id);

	@CachePut(value = "PatientsCache")
	public PatientDto updateById(Patient user);

	public PatientDto partialUpdate(int id, String gender);

}
