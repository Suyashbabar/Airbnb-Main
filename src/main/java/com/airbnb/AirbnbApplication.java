package com.airbnb;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@SpringBootApplication
public class AirbnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(){
		return new SecurityFilterChain() {
			@Override
			public boolean matches(HttpServletRequest request) {
				return false;
			}

			@Override
			public List<Filter> getFilters() {
				return List.of();
			}
		};
	}

}
