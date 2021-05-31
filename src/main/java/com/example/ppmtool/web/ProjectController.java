package com.example.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.service.MapValidationService;
import com.example.ppmtool.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationService validationService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		
		ResponseEntity<?> errorMap =validationService.errorSevice(result);
		if(errorMap !=null) {
			return errorMap;
		}
		
		Project newProject = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(newProject,HttpStatus.CREATED);
	}
	
	@GetMapping("/{identifier}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable("identifier") String identifier) {
		
		Project project = projectService.findByIdentifier(identifier);
		
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects() {
		return projectService.findAllProjects();
		
	}
	
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable("identifier") String identifier){
		
		projectService.deleteProjectByIdentifier(identifier);
		
		return new ResponseEntity<String>("Project was deleted", HttpStatus.OK);
		
	}
	

}
