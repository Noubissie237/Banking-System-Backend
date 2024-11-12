package com.banking_system.service_admin.repositories;

import com.banking_system.service_admin.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer>{ 
}
