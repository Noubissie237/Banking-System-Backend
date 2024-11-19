package com.banking_system.service_users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.banking_system.service_users.models.Agent;


@RepositoryRestResource
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    public Optional<Agent> findByMatricule(String matricule);

    public Optional<Agent> findByTel(String tel);
}
