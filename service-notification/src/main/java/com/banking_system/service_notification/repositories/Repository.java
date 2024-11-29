package com.banking_system.service_notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.banking_system.service_notification.models.Notification;
import java.util.List;


@RepositoryRestResource
public interface Repository extends JpaRepository<Notification, Integer>{
    List<Notification> findByDestinataire(String destinataire);
}
