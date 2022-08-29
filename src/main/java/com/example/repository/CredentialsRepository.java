package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Credentials;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {
	Credentials findByUsername(String username);
}
