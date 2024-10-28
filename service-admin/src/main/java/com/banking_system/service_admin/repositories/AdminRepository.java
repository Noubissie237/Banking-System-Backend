package com.banking_system.service_admin.repositories;

import com.banking_system.service_admin.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Demande, Integer> {
}
