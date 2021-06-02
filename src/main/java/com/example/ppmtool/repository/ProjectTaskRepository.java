package com.example.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ppmtool.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

}
