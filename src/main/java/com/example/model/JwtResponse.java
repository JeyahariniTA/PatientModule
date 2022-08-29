package com.example.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jwtToken;

	public JwtResponse(String token) {
		this.jwtToken = token;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
