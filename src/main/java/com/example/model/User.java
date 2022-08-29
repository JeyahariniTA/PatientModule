package com.example.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.auditable.Auditable;

@Entity
@Table(name = "users")
@NamedNativeQuery(name = "User.findRoleUsersByProjectId", query = "SELECT u.name as username, r.name as role, p.name as project FROM Users u INNER JOIN role r on u.role_id = r.id INNER JOIN users_projects_mapping upm ON"
		+ " upm.user_id = u.id INNER JOIN project p ON p.Id = upm.project_id WHERE upm.project_id =?1")
public class User extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private String dob;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "userdetail_id", referencedColumnName = "id")
//	private UserDetails userdetails;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "address_id", referencedColumnName = "id")
//	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Address> addresses;

	@ManyToOne(fetch = FetchType.LAZY)
	// @JsonManagedReference
	private Role role;
//	@ManyToOne
//    @JoinColumn(name="roleId",insertable=true,updatable=true,nullable=true)
	// @JsonManagedReference

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usersProjectsMapping", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
	// @JsonManagedReference
	private List<Project> projects;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private List<Role> role;

//	public List<Role> getRole() {
//		return role;
//	}
//
//	public void setRole(List<Role> role) {
//		this.role = role;
//	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public UserDetails getUserdetails() {
//		return userdetails;
//	}
//
//	public void setUserdetails(UserDetails userdetails) {
//		this.userdetails = userdetails;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id: " + this.id + " name: " + this.name + "address: " + addresses.toString() + "	role"
				+ role.toString() + "	projects: " + projects.toString();
	}

}
