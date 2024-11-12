package com.banking_system.service_users.repositories;

import com.banking_system.service_users.models.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Optional<Client> findByTel(String tel);
}
