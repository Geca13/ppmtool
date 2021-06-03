package com.example.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.repository.BacklogRepository;
import com.example.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	ProjectTaskRepository ptRepository;
	
	public ProjectTask addProjectTask(String identifier ,ProjectTask task) {
		Backlog backlog = backlogRepository.findByIdentifier(identifier);
		task.setBacklog(backlog);
		Integer sequence = backlog.getPtSequence();
		sequence++;
		task.setProjectSequence(identifier+"-"+sequence);
		task.setIdentifier(identifier);
		if(task.getPriority() == 0 || task.getPriority() == null ) {
			task.setPriority(3);
		}
		if(task.getStatus() == "" || task.getStatus()==null) {
			task.setStatus("TO_DO");
		}
		return ptRepository.save(task);
		
	}

}
