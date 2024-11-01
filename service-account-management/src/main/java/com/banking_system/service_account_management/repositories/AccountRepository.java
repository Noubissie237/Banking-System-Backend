package com.banking_system.service_account_management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking_system.service_account_management.models.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByNumber(String number);
}
