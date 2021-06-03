package com.example.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppmtool.domain.ProjectTask;
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
	
	@PostMapping("/{backlog_id")
	public ResponseEntity<?> addProjectToBacklog(@Valid @RequestBody ProjectTask task, @PathVariable String backlog_id, BindingResult result){
		     ResponseEntity<?> errorMap = mvService.errorSevice(result);
		     if(errorMap != null) {
		    	 return errorMap;
		     }
		     ProjectTask newTask = taskService.addProjectTask(backlog_id, task);
		return new ResponseEntity<ProjectTask>(newTask,HttpStatus.CREATED);
		
	}

}
