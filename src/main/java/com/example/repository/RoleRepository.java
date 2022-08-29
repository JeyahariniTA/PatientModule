package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public List<Role> findByRole(String roleName);

//	@Query("SELECT r.name FROM Role r join r.users WHERE r.user_id = :userId")
//	@Query("SELECT u.role.name FROM users u WHERE u.id = :userId")
//	@Query("select r from role r JOIN r.users u where u.id = :userId")
//	@Query("select u from users u JOIN u.role where u.id = :userId")
//	RoleDto findRoleByUserId(@Param("userId") int userId);

//	RoleDto findByUsers_id(int userId);
// SELECT r FROM Role r JOIN FETCH UserDetails = WHERE .name = :name
	public Role findByUsers_Id(int userid);

}
