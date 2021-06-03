package com.example.ppmtool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppmtool.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
	
	List<ProjectTask> findByIdentifierOrderByPriority(String identifier);
	
	ProjectTask findByProjectSequence(String sequence);

}
