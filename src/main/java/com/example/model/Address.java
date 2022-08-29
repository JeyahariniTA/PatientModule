package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029509033485224749L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "doorNo")
	private String doorNo;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "type")
	private String type;

//	@OneToOne(mappedBy = "address")
//	private UserDto user;
//	private UserDto userDto;

//	public UserDto getUser() {
//		return user;
//	}
//
//	public void setUser(UserDto user) {
//		this.user = user;
//	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
