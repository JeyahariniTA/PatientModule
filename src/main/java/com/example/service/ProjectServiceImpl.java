package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Project;
import com.example.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserService userService;

	@Override
	public List<Project> listProjects() {
		ArrayList<Project> projects = new ArrayList<>();
		projectRepository.findAll().forEach(project -> projects.add(project));
		// TODO Auto-generated method stub
		return projects;
	}

	@Override
	public Project addProject(Project project) {

//		List<User> users = new ArrayList<>();
//		for (User user : project.getUsers()) {
//			users.add(userService.getUserById(user.getId()));
//		}
//		project.setUsers(users);
		return projectRepository.save(project);
	}

}
