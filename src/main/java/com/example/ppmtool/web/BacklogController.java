package com.example.ppmtool.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ppmtool.domain.Project;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.exceptions.ProjectNotFoundException;
import com.example.ppmtool.repository.ProjectRepository;
import com.example.ppmtool.service.MapValidationService;
import com.example.ppmtool.service.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private ProjectTaskService taskService;
	
	@Autowired
	private MapValidationService mvService;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addProjectToBacklog(@Valid @RequestBody ProjectTask task, @PathVariable String backlog_id, BindingResult result){
		     ResponseEntity<?> errorMap = mvService.errorSevice(result);
		     if(errorMap != null) {
		    	 return errorMap;
		     }
		     ProjectTask newTask = taskService.addProjectTask(backlog_id, task);
		return new ResponseEntity<ProjectTask>(newTask,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {
		
		Project project = projectRepository.findByIdentifier(backlog_id);
		if(project == null) {
			throw new ProjectNotFoundException("Project with identifier: " + backlog_id + " doesnt exist");
		}
		
		return taskService.findBacklogById(backlog_id);
	}

	@GetMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
		ProjectTask task = taskService.findBySequence(backlog_id,pt_id);
		return new ResponseEntity<ProjectTask>(task, HttpStatus.OK);   
	}
	
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updated, @PathVariable String backlog_id, @PathVariable String pt_id, BindingResult result){
		
		ResponseEntity<?> errorMap = mvService.errorSevice(result);
	     if(errorMap != null) {
	    	 return errorMap;
	     }
	     
		ProjectTask task = taskService.updateByProjectSequence(updated, backlog_id, pt_id);
		
		return new ResponseEntity<ProjectTask>(task, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
		
		taskService.deleteTaskBySequence(backlog_id, pt_id);
		
		return new ResponseEntity<String>("Project task with id: " + pt_id+ " was deleted", HttpStatus.OK);
	}
}
