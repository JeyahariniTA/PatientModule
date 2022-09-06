package com.example.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.example.model.Patient;
import com.example.model.PatientDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PatientConverter {

	@Mapping(target = "patientAddressDto.city", source = "patientAddress.city")
	@Mapping(target = "patientAddressDto.state", source = "patientAddress.state")
	@Mapping(target = "patientAddressDto.country", source = "patientAddress.country")
	@Mapping(target = "patientAddressDto.zipCode", source = "patientAddress.zipCode")
	@Mapping(target = "patientAddressDto.email", source = "patientAddress.email")
	@Mapping(target = "patientAddressDto.mobile", source = "patientAddress.mobile")
	@Mapping(source = "patientAddress", target = "patientAddressDto")
	PatientDto convertPatientToDto(Patient patient);

	@Mapping(source = "patientAddressDto", target = "patientAddress")
	Patient convertDtoToPatient(PatientDto dto);

	@Mapping(source = "patientAddress", target = "patientAddressDto")
	@Mapping(source = "patientAddressDto.city", target = "patientAddress.city")
	@Mapping(source = "patientAddressDto.state", target = "patientAddress.state")
	@Mapping(source = "patientAddressDto.country", target = "patientAddress.country")
	@Mapping(source = "patientAddressDto.zipCode", target = "patientAddress.zipCode")
	@Mapping(source = "patientAddressDto.email", target = "patientAddress.email")
	@Mapping(source = "patientAddressDto.mobile", target = "patientAddress.mobile")
	List<PatientDto> convertPatientsToDtos(List<Patient> listUsers);

	@Mapping(source = "patientAddressDto", target = "patientAddress")
	List<Patient> convertDtosToPatients(List<PatientDto> dtos);

}
