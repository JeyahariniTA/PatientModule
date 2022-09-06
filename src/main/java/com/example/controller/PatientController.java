package com.example.controller;

import java.util.List;
import org.json.JSONObject;
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

import com.example.converter.PatientConverter;
import com.example.exception.CustomException;
import com.example.model.Patient;
import com.example.model.PatientDto;
import com.example.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/patient")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
	@Autowired
	PatientService dao;

	@Autowired
	PatientConverter patientConverter;

	@GetMapping(path = "/Hello")
	@ResponseBody
	public String sayHello() {
		return "Hello!";
	}

	@GetMapping(path = "/get")
	@ResponseBody
	public ResponseEntity<String> getPatients() {
		List<PatientDto> patientList = dao.listPatients();
		JSONObject patientObj = new JSONObject();
		// for (PatientDto dto : patientList) {
		PatientDto dto = new PatientDto();
		JSONObject json = null;
		for (int i = 0; i < patientList.size(); i++) {
			dto = patientList.get(i);
			json = new JSONObject();
			json.put("sex", dto.getSex());
			json.put("mrn", dto.getMedicalRecordNumber());
			json.put("firstname", dto.getFirstName());
			json.put("lastName", dto.getLastName());
			json.put("status", dto.getStatus());
			json.put("maritalStatus", dto.getMaritalStatus());
			json.put("startOfCareDate", dto.getStartOfCareDate());
			json.put("dob", dto.getDob());
			patientObj.put(String.valueOf(dto.getId()), json);

		}
		return new ResponseEntity<>(patientObj.toString(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/get/{patientId}")
	@ResponseBody
	public ResponseEntity<String> getPatientById(@PathVariable("patientId") int patientId) {
		try {
			PatientDto patientDto = dao.getPatientById(patientId);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(patientDto);
			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put("Message", e.getLocalizedMessage());
			return new ResponseEntity<>(json.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/get/{pageNo}/{patientsCount}")
	@ResponseBody
	public List<PatientDto> getPatientsWithPagination(@PathVariable("pageNo") int pageNo,
			@PathVariable("patientsCount") int patientsCount) {
		return dao.listPatientsWithPagination(pageNo, patientsCount);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addPatient(@RequestBody Patient patient) {
		if (null != patient.getDob()) {
			boolean isValidDate = false;
			String dob = patient.getDob().toString();
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
			String dob = patient.getDob().toString();
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

	@GetMapping(path = "/getByMrn/{mrn}")
	@ResponseBody
	public ResponseEntity<String> getPatientByMrn(@PathVariable("mrn") int mrn) {
		PatientDto dto = dao.getPatientIdByMedicalRecordNumber(mrn);
		if (dto.getId() == 0) {
			throw new CustomException("Given MRN does not exists", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(String.valueOf(dto.getId()), HttpStatus.OK);
		}
	}

}
