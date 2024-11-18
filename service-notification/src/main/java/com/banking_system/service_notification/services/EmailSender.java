package com.banking_system.service_notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mail;

    public void sendMail(String toEmail,
                         String subject,
                         String body) throws MessagingException {
        
        // Crée un objet MimeMessage
        MimeMessage message = mail.createMimeMessage();
        
        // Utilise MimeMessageHelper pour spécifier le format HTML
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Définir le contenu HTML et la signature
        String signature = "<br><br><center>----</center><br>" +
                           "<span style='color: green; font-weight: bold; font-size:14px;'> Quick Send Team</span><br>" + "<span style='color: orange; font-weight: bold; font-size:13px;'> supp0rt.quicksend@gmail.com</span><br><br>" +
                           "<img src='cid:logoImage' alt='Quick Send' width='45%' height='100px'/>";
        String htmlContent = body + signature;

        helper.setFrom("supp0rt.quicksend@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); 
        helper.addInline("logoImage", new ClassPathResource("/img/bs.jpg"));

        
        mail.send(message);

        System.out.println("Mail envoyé avec succès!");
    }
}
