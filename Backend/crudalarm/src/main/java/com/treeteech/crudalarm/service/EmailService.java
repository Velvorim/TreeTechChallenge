package com.treeteech.crudalarm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailService {
    
    @Autowired
	private MailSender mailSender;

    public void sendEmail() {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("ttestesdeenviosdeemail@gmail.com");
		sm.setFrom("ttestesdeenviosdeemail@gmail.com");
		sm.setSubject("Cadastro de Alarme");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Um alarme com a classificação Alto foi cadastrado!");
		mailSender.send(sm);
	}


}
