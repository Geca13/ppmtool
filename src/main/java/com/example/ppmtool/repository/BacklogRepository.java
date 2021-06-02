package com.example.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ppmtool.domain.Backlog;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
	
	Backlog findByIdentifier(String identifier);

}
