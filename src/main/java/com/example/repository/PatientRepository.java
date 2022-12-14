package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public Patient getByMedicalRecordNumber(int mrn);

	public boolean existsByMedicalRecordNumber(int mrn);

}
