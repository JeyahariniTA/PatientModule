package com.example.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Credentials;
import com.example.model.CredentialsDto;
import com.example.repository.CredentialsRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	CredentialsRepository repository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("harini".equals(username)) {
//			return new User("harini", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Credentials credential = repository.findByUsername(username);
		if (credential == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(credential.getUsername(),
				credential.getPassword(), new ArrayList<>());
	}

	public Credentials save(CredentialsDto credentials) {
		Credentials newCredential = new Credentials();
		newCredential.setUsername(credentials.getUsername());
		newCredential.setPassword(bcryptEncoder.encode(credentials.getPassword()));
		return repository.save(newCredential);
	}

}
