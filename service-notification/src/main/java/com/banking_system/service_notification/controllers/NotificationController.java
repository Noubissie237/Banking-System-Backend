package com.banking_system.service_notification.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_notification.events.RetraitProducer;
import com.banking_system.service_notification.models.Notification;
import com.banking_system.service_notification.services.NotificationService;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class NotificationController {
    
    @Autowired
    NotificationService notificationService;

    @PostMapping("/ask-retrait")
    public void postMethodName(@RequestBody RetraitProducer retrait) throws IOException, MessagingException {        
        notificationService.askRetrait(retrait);
    }

    @PostMapping("/send-notification")
    public void postMethodName(@RequestBody Notification entity) {
        notificationService.sendNotification(entity);
    }
    
    @GetMapping("/get-notification")
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/get-notification/{number}")
    public List<Notification> getNotificationsUser(@PathVariable String number) {
        return notificationService.getAllNotificationsUser(number);
    }    
    
}
