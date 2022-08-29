package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.example.model.Project;

public interface ProjectService {

	@Cacheable(value = "ProjectsCache")
	public List<Project> listProjects();

	public Project addProject(Project project);

}
