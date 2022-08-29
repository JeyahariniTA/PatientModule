package com.example.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.auditable.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String role;

//	@OneToMany(mappedBy = "role")
//	@JsonManagedReference
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	// @JsonBackReference
	@JsonIgnore
	private List<User> users;
//	@JoinColumn(name = "roleId")
//	@JsonBackReference

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return "{roleid: " + id + "role: " + role + "}";
	}

}
