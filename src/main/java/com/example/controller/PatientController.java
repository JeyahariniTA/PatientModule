package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CustomException;
import com.example.model.Patient;
import com.example.model.PatientDto;
import com.example.service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/patient")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
	@Autowired
	PatientService dao;

	@GetMapping(path = "/Hello")
	@ResponseBody
	public String sayHello() {
		return "Hello!";
	}

	@GetMapping(path = "/get")
	@ResponseBody
	public List<PatientDto> getPatients() {
		return dao.listPatients();
	}

	@GetMapping(path = "/get/{patientId}")
	@ResponseBody
	public PatientDto getPatientById(@PathVariable("patientId") int patientId) {
		return dao.getPatientById(patientId);
	}

	@GetMapping(path = "/get/{pageNo}/{userCount}")
	@ResponseBody
	public List<PatientDto> getPatientsWithPagination(@PathVariable("pageNo") int pageNo,
			@PathVariable("patientsCount") int patientsCount) {
		return dao.listPatientsWithPagination(pageNo, patientsCount);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addPatient(@RequestBody Patient patient) {
		if (null != patient.getDob()) {
			boolean isValidDate = false;
			String dob = patient.getDob();
			String regex = "^([0-9]{4})-(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])$";
			isValidDate = dob.matches(regex);

			if (isValidDate) {
				PatientDto patientDto = dao.addPatient(patient);
				if (patientDto.getId() == 0) {
					throw new CustomException("Patient not exists", HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<>(patientDto, HttpStatus.OK);
				}
			} else {
				throw new CustomException("Entry not added. Please provide valid date format - (yyyy-mm-dd)",
						HttpStatus.BAD_REQUEST);
			}
		} else {
			PatientDto patientDto = dao.addPatient(patient);
			if (patientDto.getId() == 0) {
				throw new CustomException("Patient not exists", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(patientDto, HttpStatus.OK);
			}
		}
	}

	@DeleteMapping(path = "/delete/{patientId}")
	public String deletePatient(@PathVariable("patientId") int patientId) {
		return dao.deletePatient(patientId);

	}

	@PutMapping(path = "/update")
	public ResponseEntity<Object> updatePatientById(@RequestBody Patient patient) {
		if (null != patient.getDob() && null != patient.getDob()) {
			boolean isValidDate = false;
			String dob = patient.getDob();
			String regex = "^([0-9]{4})-(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])$";
			isValidDate = dob.matches(regex);
			PatientDto patientDto;
			if (isValidDate) {
				patientDto = dao.updateById(patient);
				if (patientDto.getId() == 0) {
					throw new CustomException("Patient not exists", HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<>(patientDto, HttpStatus.OK);
				}
			} else {
				throw new CustomException("Entry not added. Please provide valid date format - (yyyy-mm-dd)",
						HttpStatus.BAD_REQUEST);
			}
		} else {
			PatientDto patientDto = dao.updateById(patient);
			if (patientDto.getId() == 0) {
				throw new CustomException("Patient not exists", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(patientDto, HttpStatus.OK);
			}
		}
	}

	@PatchMapping("/update/{id}/{gender}")
	public ResponseEntity<Object> updatePartially(@PathVariable("id") int id, @PathVariable("gender") String gender) {
		PatientDto patientDto = dao.partialUpdate(id, gender);
		return new ResponseEntity<>(patientDto, HttpStatus.ACCEPTED);
	}

}
