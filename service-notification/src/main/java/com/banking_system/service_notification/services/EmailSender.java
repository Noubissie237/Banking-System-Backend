package com.banking_system.service_notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

     @Autowired
    private JavaMailSender mail;

    public void sendMail(String toEmail,
                        String subject,
                        String body){
                
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("pharma.prjt.yde@gmail.com");
                message.setTo(toEmail);
                message.setText(body);
                message.setSubject(subject);

                mail.send(message);

                System.out.println("Mail envoyer avec succes!");
                        }
    
}
