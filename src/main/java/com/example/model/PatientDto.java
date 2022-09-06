package com.example.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999671781205117900L;
	private int id;
	private int medicalRecordNumber;
	private Date startOfCareDate;
	private String status;
	private String firstName;
	private String lastName;
	private String sex;
	private Date dob;
	private String maritalStatus;
	private PatientAddressDto patientAddressDto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(int medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public Date getStartOfCareDate() {
		return startOfCareDate;
	}

	public void setStartOfCareDate(Date startOfCareDate) {
		this.startOfCareDate = startOfCareDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public PatientAddressDto getPatientAddressDto() {
		return patientAddressDto;
	}

	public void setPatientAddressDto(PatientAddressDto patientAddressDto) {
		this.patientAddressDto = patientAddressDto;
	}

}
