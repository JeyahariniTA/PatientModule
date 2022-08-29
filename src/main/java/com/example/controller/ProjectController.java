package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Project;
import com.example.service.ProjectService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/project")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(path = "/get")
	public List<Project> listProjects() {
		List<Project> projects = projectService.listProjects();
		return projects;
	}

	@PostMapping(path = "/add")
	public Project addProject(@RequestBody Project project) {
		return projectService.addProject(project);
	}

}
