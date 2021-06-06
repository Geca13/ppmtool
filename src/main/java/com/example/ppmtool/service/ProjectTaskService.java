package com.example.ppmtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.exceptions.ProjectNotFoundException;
import com.example.ppmtool.repository.BacklogRepository;
import com.example.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	ProjectTaskRepository ptRepository;
	
	public ProjectTask addProjectTask(String identifier ,ProjectTask task) {
		
		try {
			
			Backlog backlog = backlogRepository.findByIdentifier(identifier);
			task.setBacklog(backlog);
			Integer sequence = backlog.getPtSequence();
			sequence++;
			backlog.setPtSequence(sequence);
			task.setProjectSequence(identifier+"-"+sequence);
			task.setIdentifier(identifier);
			if(task.getPriority() == 0 ||task.getPriority() == null ) {
				task.setPriority(3);
			}
			if(task.getStatus() == "" || task.getStatus()==null) {
				task.setStatus("TO_DO");
			}
			return ptRepository.save(task);
			
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project not found");
		}
		
	}

	public Iterable<ProjectTask> findBacklogById(String backlog_id) {
		
		return ptRepository.findByIdentifierOrderByPriority(backlog_id);
	}
	
	
	public ProjectTask findBySequence(String backlog_id, String pt_id) {
		
		Backlog backlog = backlogRepository.findByIdentifier(backlog_id) ;
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with identifier: " + backlog_id + " doesnt exist");
		}
		
		ProjectTask task = ptRepository.findByProjectSequence(pt_id);
		if(task == null) {
			throw new ProjectNotFoundException("Project with identifier: " + pt_id + " doesnt exist");
        }
		
		if(!task.getIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project task with identifier: " + pt_id + " doesnt exist in project " + backlog_id);
        }
		
		return task;
	}
	
	public ProjectTask updateByProjectSequence (ProjectTask updated, String backlog_id , String pt_id) {
		
		ProjectTask task = findBySequence(backlog_id, pt_id);
		
		task = updated;
		
		return ptRepository.save(task);
	}
	
	public void deleteTaskBySequence(String backlog_id , String pt_id) {
		
		ProjectTask task = findBySequence(backlog_id, pt_id);
		
		ptRepository.delete(task);
		
	}

}
