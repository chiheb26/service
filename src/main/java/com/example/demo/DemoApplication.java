package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/ip")
	public String getIpAddress(HttpServletRequest request) {
		String xff = request.getHeader("X-Forwarded-For");
		if (xff != null) {
			return xff.split(",")[0];
		}
		return request.getRemoteAddr();
	}

}
