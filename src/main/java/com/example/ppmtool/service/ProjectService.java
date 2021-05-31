package com.example.ppmtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.exceptions.IdentifierException;
import com.example.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		try {
			project.setIdentifier(project.getIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new IdentifierException("Project id " + project.getIdentifier().toUpperCase()+ " already exist");
		}
		
	}
	
	public Project findByIdentifier(String identifier) {
		
		Project project = projectRepository.findByIdentifier(identifier.toUpperCase());
		
		if(project == null) {
			throw new IdentifierException("Project id " + identifier+ " doesn't exist");

		}
		
		return project;
	}
	
	public Iterable<Project> findAllProjects () {
		return projectRepository.findAll();
		
	}
	
	public void deleteProjectByIdentifier(String identifier) {
		Project project = projectRepository.findByIdentifier(identifier.toUpperCase());
		
		if(project == null) {
			throw new IdentifierException("Cant find Project with identifier " + identifier);
        }
		projectRepository.delete(project);
    }
	
	
	

}
