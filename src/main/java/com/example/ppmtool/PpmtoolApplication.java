package com.example.ppmtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
 //import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class PpmtoolApplication {
	
//	@Bean
	//BCryptPasswordEncoder encoder() {
	//	return new BCryptPasswordEncoder();
//	}
	 

	public static void main(String[] args) {
		SpringApplication.run(PpmtoolApplication.class, args);
	}

}
