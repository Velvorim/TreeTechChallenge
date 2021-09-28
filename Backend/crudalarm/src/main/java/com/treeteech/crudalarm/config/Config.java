package com.treeteech.crudalarm.config;

import com.treeteech.crudalarm.service.EmailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    

    @Bean
	public EmailService emailService() {
		return new EmailService();
	}
}
