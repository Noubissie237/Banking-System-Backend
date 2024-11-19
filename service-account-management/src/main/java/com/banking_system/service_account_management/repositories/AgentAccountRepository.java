package com.banking_system.service_account_management.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking_system.service_account_management.models.AgentAccount;
import java.util.Optional;



@Repository
public interface AgentAccountRepository extends JpaRepository<AgentAccount, Integer> {
    Optional<AgentAccount> findByMatricule(String matricule);
}