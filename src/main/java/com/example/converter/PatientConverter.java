package com.example.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.example.model.Patient;
import com.example.model.PatientDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PatientConverter {

	PatientDto convertPatientToDto(Patient user);

	Patient convertDtoToUser(PatientDto dto);

	List<PatientDto> convertPatientsToDtos(List<Patient> listUsers);

	List<Patient> convertDtosToPatients(List<PatientDto> dtos);

}
