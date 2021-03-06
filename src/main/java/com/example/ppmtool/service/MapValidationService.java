package com.example.ppmtool.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.ppmtool.repository.ProjectRepository;



@Service
public class MapValidationService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ResponseEntity<?> errorSevice(BindingResult result){
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : result.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
				
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		
		return null;
		
	}

}
