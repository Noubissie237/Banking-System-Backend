package com.banking_system.service_account_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking_system.service_account_management.models.AgentAccount;

@Repository
public interface AgentAccountRepository extends JpaRepository<AgentAccount, Integer> {
}
