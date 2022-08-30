package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.converter.PatientConverter;
import com.example.model.Patient;
import com.example.model.PatientDto;
import com.example.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	PatientConverter patientConverter;

	@Override
	public List<PatientDto> listPatients() {
		return patientConverter.convertPatientsToDtos(patientRepository.findAll());
	}

	@Override
	public PatientDto getPatientById(int id) {
		try {
			if (patientRepository.existsById(id)) {
				return patientConverter.convertPatientToDto(patientRepository.findById(id).get());
			} else {
				return new PatientDto();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public PatientDto addPatient(Patient patient) {
		return patientConverter.convertPatientToDto(patientRepository.save(patient));
	}

	@Override
	public String deletePatient(int id) {
		if (patientRepository.existsById(id)) {
			patientRepository.deleteById(id);
			return "Deleted Successfully!";
		} else {
			return "Please provide valid user id to delete.";
		}
	}

	@Override
	@CachePut(value = "UsersUpdate")
	public PatientDto updateById(Patient patient) {
		if (patientRepository.existsById(patient.getId())) {
			return patientConverter.convertPatientToDto(patientRepository.save(patient));
		} else {
			return new PatientDto();
		}
	}

	@Override
	public List<PatientDto> listPatientsWithPagination(int pageNo, int usersCount) {
		List<PatientDto> list = new ArrayList<>();
		PageRequest pageReq = PageRequest.of(pageNo, usersCount);
		list = patientConverter.convertPatientsToDtos(patientRepository.findAll(pageReq).getContent());
		return list;
	}

	@Override
	public PatientDto partialUpdate(int id, String gender) {
		PatientDto userDto = new PatientDto();
		if (patientRepository.existsById(id)) {
			Patient patient = patientRepository.findById(id).get();
			patient.setGender(gender);
			userDto = patientConverter.convertPatientToDto(patientRepository.save(patient));
		}
		return userDto;
	}

}
