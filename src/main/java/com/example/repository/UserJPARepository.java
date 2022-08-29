package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Role;
import com.example.model.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {

	List<User> findByName(String name);

//	List<User> findUserDataRightJoinByRoleId(int roleId);

	List<User> findByRoleId(int id);

	public Role findRoleById(int id);

	@Query(value = "SELECT u.id, u.name FROM Users u INNER JOIN users_projects_mapping upm ON upm.user_Id = u.id WHERE upm.project_id =:projectId", nativeQuery = true)
	List<Object[]> findUsersByProjectId(@Param("projectId") int projectId);

	public List<Object[]> findRoleUsersByProjectId(int projectId);
}
